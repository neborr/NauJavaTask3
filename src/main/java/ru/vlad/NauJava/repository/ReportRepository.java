package ru.vlad.NauJava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vlad.NauJava.entity.Report;

public interface ReportRepository extends JpaRepository<Report, Long> {
}