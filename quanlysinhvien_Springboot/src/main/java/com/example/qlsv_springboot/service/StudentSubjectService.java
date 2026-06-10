package com.example.qlsv_springboot.service;

import com.example.qlsv_springboot.entity.StudentSubject;

import java.util.List;

public interface StudentSubjectService {

    Integer countRegisteredSubjects(String studentId);

    List<StudentSubject> getScoresByStudent(String studentId);

    StudentSubject inputScore(
            String studentId,
            String subjectId,
            Double processScore,
            Double finalScore
    );

    String getPassFailResult(String studentId, String subjectId);
}