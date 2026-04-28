package ru.vlad.NauJava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import ru.vlad.NauJava.entity.Movie;
import ru.vlad.NauJava.entity.Report;
import ru.vlad.NauJava.entity.ReportStatus;
import ru.vlad.NauJava.repository.ReportRepository;
import ru.vlad.NauJava.repository.UserRepository;
import ru.vlad.NauJava.repository.MovieRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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

    @Async
    public void generateReportAsync(Long reportId) {
        long totalStartTime = System.currentTimeMillis();
        try {
            long userCalcStart = System.currentTimeMillis();
            long userCount = userRepository.count();
            long userCalcDuration = System.currentTimeMillis() - userCalcStart;

            long movieCalcStart = System.currentTimeMillis();
            List<Movie> movies = StreamSupport
                    .stream(movieRepository.findAll().spliterator(), false)
                    .toList();
            long movieCalcDuration = System.currentTimeMillis() - movieCalcStart;

            long totalDuration = System.currentTimeMillis() - totalStartTime;

            StringBuilder html = new StringBuilder();
            html.append("<html><head><meta charset='UTF-8'></head><body style='font-family: sans-serif; padding: 20px;'>")
                    .append("<h1>Отчет по статистике приложения</h1>")

                    .append("<h3>1. Сводная статистика времени:</h3>")
                    .append("<table border='1' cellpadding='8' style='border-collapse: collapse; margin-bottom: 20px;'>")
                    .append("<tr style='background-color: #f2f2f2;'><th>Параметр</th><th>Значение</th><th>Время обработки</th></tr>")
                    .append("<tr><td>Количество пользователей</td><td>").append(userCount).append("</td><td>").append(userCalcDuration).append(" мс</td></tr>")
                    .append("<tr><td>Список объектов (Фильмы)</td><td>Загружено: ").append(movies.size()).append("</td><td>").append(movieCalcDuration).append(" мс</td></tr>")
                    .append("<tr><td><b>ИТОГО время формирования</b></td><td colspan='2' style='text-align: center;'><b>").append(totalDuration).append(" мс</b></td></tr>")
                    .append("</table>")

                    .append("<h3>2. Список объектов сущности Movie:</h3>")
                    .append("<table border='1' cellpadding='8' style='border-collapse: collapse; width: 100%; text-align: left;'>")
                    .append("<tr style='background-color: #f2f2f2;'><th>ID</th><th>Название</th><th>Жанр</th><th>Рейтинг</th></tr>");

            if (movies.isEmpty()) {
                html.append("<tr><td colspan='4' style='text-align:center;'>В базе данных нет записей о фильмах</td></tr>");
            } else {
                for (Movie movie : movies) {
                    html.append("<tr>")
                            .append("<td>").append(movie.getId()).append("</td>")
                            .append("<td>").append(movie.getTitle()).append("</td>")
                            .append("<td>").append(movie.getGenre()).append("</td>")
                            .append("<td>").append(movie.getAgeRating() != null ? movie.getAgeRating() : "—").append("</td>")
                            .append("</tr>");
                }
            }

            html.append("</table></body></html>");

            updateReport(reportId, html.toString(), ReportStatus.COMPLETED);

        } catch (Exception e) {
            updateReport(reportId, "Ошибка: " + e.getMessage(), ReportStatus.ERROR);
        }
    }

    private void updateReport(Long id, String content, ReportStatus status) {
        reportRepository.findById(id).ifPresent(report -> {
            report.setContent(content);
            report.setStatus(status);
            reportRepository.save(report);
        });
    }
}