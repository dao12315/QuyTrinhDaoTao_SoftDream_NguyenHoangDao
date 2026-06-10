package com.example.qlsv_springboot.controller;

import com.example.qlsv_springboot.entity.Subject;
import com.example.qlsv_springboot.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subjects")
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectService subjectService;

    @GetMapping
    public List<Subject> getAllSubjects() {
        return subjectService.getAllSubjects();
    }

    @GetMapping("/{subjectId}")
    public Subject getSubjectDetail(@PathVariable String subjectId) {
        return subjectService.getSubjectDetail(subjectId);
    }
}