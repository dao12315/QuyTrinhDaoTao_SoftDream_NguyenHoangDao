package com.example.qlsv_springboot.service.impl;

import com.example.qlsv_springboot.entity.Student;
import com.example.qlsv_springboot.repository.StudentRepository;
import com.example.qlsv_springboot.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudentDetail(String studentId) {
        return studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sinh viên"));
    }
    @Override
    public Student updateStudent(String studentId, Student student) {
        Student oldStudent = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sinh viên"));

        oldStudent.setFullName(student.getFullName());
        oldStudent.setGender(student.getGender());
        oldStudent.setDateOfBirth(student.getDateOfBirth());
        oldStudent.setClassName(student.getClassName());
        oldStudent.setCourse(student.getCourse());

        return studentRepository.save(oldStudent);
    }
}