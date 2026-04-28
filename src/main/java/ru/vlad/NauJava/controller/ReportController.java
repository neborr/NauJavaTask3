package ru.vlad.NauJava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vlad.NauJava.entity.ReportStatus;
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
    public String createReport() {
        Long id = reportService.createReport();
        reportService.generateReportAsync(id);
        return "Отчет запущен. ID: " + id;
    }

    @GetMapping(value = "/{id}", produces = "text/html; charset=UTF-8")
    public ResponseEntity<String> getReport(@PathVariable Long id) {
        return reportRepository.findById(id)
                .map(report -> {
                    if (report.getStatus() == ReportStatus.COMPLETED) {
                        return ResponseEntity.ok(report.getContent());
                    }
                    return ResponseEntity.ok("Статус отчета: " + report.getStatus());
                })
                .orElse(ResponseEntity.notFound().build());
    }
}