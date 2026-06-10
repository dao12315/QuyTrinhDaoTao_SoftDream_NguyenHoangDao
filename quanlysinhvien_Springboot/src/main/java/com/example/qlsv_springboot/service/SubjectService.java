package com.example.qlsv_springboot.service;

import com.example.qlsv_springboot.entity.Subject;

import java.util.List;

public interface SubjectService {

    List<Subject> getAllSubjects();

    Subject getSubjectDetail(String subjectId);
}