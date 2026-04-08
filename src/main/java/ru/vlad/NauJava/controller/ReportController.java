package ru.vlad.NauJava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.vlad.NauJava.entity.Report;
import ru.vlad.NauJava.repository.ReportRepository;
import ru.vlad.NauJava.service.ReportService;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @Autowired
    private ReportRepository reportRepository;

    @PostMapping
    public String startReport() {
        Long id = reportService.createReport();
        reportService.generateReportAsync(id);
        return "Процесс формирования отчета запущен. ID: " + id;
    }

    @GetMapping("/{id}")
    public String getReport(@PathVariable Long id) {
        Report report = reportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Отчет с таким ID не найден"));

        return switch (report.getStatus()) {
            case CREATED -> "Отчет еще в процессе формирования. Попробуйте обновить страницу позже.";
            case ERROR -> "Произошла ошибка при генерации данного отчета.";
            case COMPLETED -> report.getContent();
        };
    }
}