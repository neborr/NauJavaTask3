package ru.vlad.NauJava.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.vlad.NauJava.model.ReportStatus;

@Entity
@Table(name = "reports")
@Getter
@Setter
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ReportStatus status;

    @Column(columnDefinition = "TEXT")
    private String content;
}