package com.example.qlsv_springboot.controller;

import com.example.qlsv_springboot.entity.Student;
import com.example.qlsv_springboot.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    // Student + Teacher đều được xem danh sách sinh viên
    @PreAuthorize("hasAnyAuthority('student', 'teacher')")
    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    // Student + Teacher đều được xem chi tiết sinh viên
    @PreAuthorize("hasAnyAuthority('student', 'teacher')")
    @GetMapping("/{studentId}")
    public Student getStudentDetail(@PathVariable String studentId) {
        return studentService.getStudentDetail(studentId);
    }

    // Chỉ Teacher được sửa thông tin sinh viên
    @PreAuthorize("hasAuthority('teacher')")
    @PutMapping("/{studentId}")
    public Student updateStudent(
            @PathVariable String studentId,
            @RequestBody Student student
    ) {
        return studentService.updateStudent(studentId, student);
    }
}