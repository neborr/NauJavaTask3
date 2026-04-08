package ru.vlad.NauJava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vlad.NauJava.entity.Report;
import ru.vlad.NauJava.model.ReportStatus;
import ru.vlad.NauJava.repository.ReportRepository;
import ru.vlad.NauJava.repository.UserRepository;
import ru.vlad.NauJava.repository.MovieRepository;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ReportService {

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MovieRepository movieRepository;

    public Long createReport() {
        Report report = new Report();
        report.setStatus(ReportStatus.CREATED);
        return reportRepository.save(report).getId();
    }

    public void generateReportAsync(Long reportId) {
        CompletableFuture.runAsync(() -> {
            long totalStartTime = System.currentTimeMillis();

            try {
                AtomicLong userCount = new AtomicLong();
                AtomicLong userTime = new AtomicLong();
                AtomicLong movieTime = new AtomicLong();

                Thread userThread = new Thread(() -> {
                    long start = System.currentTimeMillis();
                    userCount.set(userRepository.count());
                    userTime.set(System.currentTimeMillis() - start);
                });

                Thread movieThread = new Thread(() -> {
                    long start = System.currentTimeMillis();
                    movieRepository.findAll();
                    movieTime.set(System.currentTimeMillis() - start);
                });

                userThread.start();
                movieThread.start();

                userThread.join();
                movieThread.join();

                long totalTime = System.currentTimeMillis() - totalStartTime;

                String html = "<html><body>" +
                        "<h1>Отчет по статистике приложения</h1>" +
                        "<table border='1'>" +
                        "<tr><th>Параметр</th><th>Значение</th><th>Затраченное время (мс)</th></tr>" +
                        "<tr><td>Количество пользователей</td><td>" + userCount.get() + "</td><td>" + userTime.get() + "</td></tr>" +
                        "<tr><td>Загрузка списка фильмов</td><td>Выполнено</td><td>" + movieTime.get() + "</td></tr>" +
                        "<tr><td><b>Общее время формирования</b></td><td colspan='2'><b>" + totalTime + " мс</b></td></tr>" +
                        "</table>" +
                        "</body></html>";

                updateReport(reportId, html, ReportStatus.COMPLETED);

            } catch (Exception e) {
                updateReport(reportId, null, ReportStatus.ERROR);
            }
        });
    }

    private void updateReport(Long id, String content, ReportStatus status) {
        Report report = reportRepository.findById(id).orElseThrow();
        if (content != null) report.setContent(content);
        report.setStatus(status);
        reportRepository.save(report);
    }
}