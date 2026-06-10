package com.example.qlsv_springboot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "subjects")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Subject {

    @Id
    @Column(name = "subject_id", length = 20)
    private String subjectId;

    @Column(name = "subject_name", length = 100)
    private String subjectName;

    @Column(name = "number_of_lessons")
    private Integer numberOfLessons;

    @Column(name = "process_rate")
    private Double processRate;

    @Column(name = "final_rate")
    private Double finalRate;

    @JsonIgnore
    @OneToMany(mappedBy = "subject")
    private List<StudentSubject> studentSubjects;
}