package com.example.qlsv_springboot.service.impl;

import com.example.qlsv_springboot.entity.Student;
import com.example.qlsv_springboot.entity.StudentSubject;
import com.example.qlsv_springboot.entity.Subject;
import com.example.qlsv_springboot.repository.StudentRepository;
import com.example.qlsv_springboot.repository.StudentSubjectRepository;
import com.example.qlsv_springboot.repository.SubjectRepository;
import com.example.qlsv_springboot.service.StudentSubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentSubjectServiceImpl implements StudentSubjectService {

    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;
    private final StudentSubjectRepository studentSubjectRepository;

    @Override
    public Integer countRegisteredSubjects(String studentId) {
        return studentSubjectRepository.countByStudentStudentId(studentId);
    }

    @Override
    public List<StudentSubject> getScoresByStudent(String studentId) {
        return studentSubjectRepository.findByStudentStudentId(studentId);
    }

    @Override
    public StudentSubject inputScore(
            String studentId,
            String subjectId,
            Double processScore,
            Double finalScore
    ) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sinh viên"));

        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy môn học"));

        StudentSubject studentSubject = studentSubjectRepository
                .findByStudentStudentIdAndSubjectSubjectId(studentId, subjectId)
                .orElse(new StudentSubject());

        studentSubject.setStudent(student);
        studentSubject.setSubject(subject);
        studentSubject.setProcessScore(processScore);
        studentSubject.setFinalScore(finalScore);

        return studentSubjectRepository.save(studentSubject);
    }

    @Override
    public String getPassFailResult(String studentId, String subjectId) {
        StudentSubject studentSubject = studentSubjectRepository
                .findByStudentStudentIdAndSubjectSubjectId(studentId, subjectId)
                .orElseThrow(() -> new RuntimeException("Sinh viên chưa đăng ký môn học này"));

        Double totalScore = studentSubject.getTotalScore();

        if (totalScore == null) {
            return "Sinh viên chưa đủ điểm để xét kết quả";
        }

        if (totalScore >= 4) {
            return "Sinh viên qua môn. Điểm tổng kết: " + totalScore;
        }

        return "Sinh viên trượt môn. Điểm tổng kết: " + totalScore;
    }
}