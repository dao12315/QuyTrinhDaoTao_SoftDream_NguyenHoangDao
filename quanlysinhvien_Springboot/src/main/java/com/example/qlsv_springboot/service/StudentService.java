package com.example.qlsv_springboot.service;

import com.example.qlsv_springboot.entity.Student;

import java.util.List;

public interface StudentService {

    List<Student> getAllStudents();

    Student getStudentDetail(String studentId);

    Student updateStudent(String studentId, Student student);
}