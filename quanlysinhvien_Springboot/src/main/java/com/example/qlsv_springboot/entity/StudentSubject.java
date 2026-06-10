package com.example.qlsv_springboot.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "student_subjects")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentSubject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "process_score")
    private Double processScore;

    @Column(name = "final_score")
    private Double finalScore;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @Transient
    public Double getTotalScore() {
        if (processScore == null || finalScore == null || subject == null) {
            return null;
        }

        Double processRate = subject.getProcessRate();
        Double finalRate = subject.getFinalRate();

        if (processRate == null || finalRate == null) {
            return null;
        }
        return processScore * processRate + finalScore * finalRate;
    }

    @Transient
    public Boolean getPassed() {
        Double totalScore = getTotalScore();

        if (totalScore == null) {
            return null;
        }

        return totalScore >= 4;
    }
}