package com.example.qlsv_springboot.controller;

import com.example.qlsv_springboot.entity.StudentSubject;
import com.example.qlsv_springboot.service.StudentSubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student-subjects")
@RequiredArgsConstructor
public class StudentSubjectController {

    private final StudentSubjectService studentSubjectService;

    // 3. Xem số môn học sinh viên đăng ký
    // Student + Teacher đều được xem
    @PreAuthorize("hasAnyAuthority('student', 'teacher')")
    @GetMapping("/students/{studentId}/count")
    public Integer countRegisteredSubjects(@PathVariable String studentId) {
        return studentSubjectService.countRegisteredSubjects(studentId);
    }

    // 4. Xem điểm môn học của sinh viên
    // Student + Teacher đều được xem
    @PreAuthorize("hasAnyAuthority('student', 'teacher')")
    @GetMapping("/students/{studentId}/scores")
    public List<StudentSubject> getScoresByStudent(@PathVariable String studentId) {
        return studentSubjectService.getScoresByStudent(studentId);
    }

    // 5. Nhập / sửa điểm của sinh viên
    // Chỉ Teacher được nhập hoặc sửa điểm
    @PreAuthorize("hasAuthority('teacher')")
    @PostMapping("/input-score")
    public StudentSubject inputScore(
            @RequestParam String studentId,
            @RequestParam String subjectId,
            @RequestParam Double processScore,
            @RequestParam Double finalScore
    ) {
        return studentSubjectService.inputScore(
                studentId,
                subjectId,
                processScore,
                finalScore
        );
    }

    // 6. Xem kết quả trượt đỗ của sinh viên
    // Student + Teacher đều được xem
    @PreAuthorize("hasAnyAuthority('student', 'teacher')")
    @GetMapping("/result")
    public String getPassFailResult(
            @RequestParam String studentId,
            @RequestParam String subjectId
    ) {
        return studentSubjectService.getPassFailResult(studentId, subjectId);
    }
}