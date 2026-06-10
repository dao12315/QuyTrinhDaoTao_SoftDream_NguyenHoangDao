package com.example.qlsv_springboot.repository;

import com.example.qlsv_springboot.entity.StudentSubject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentSubjectRepository extends JpaRepository<StudentSubject, Integer> {

    List<StudentSubject> findByStudentStudentId(String studentId);

    Optional<StudentSubject> findByStudentStudentIdAndSubjectSubjectId(
            String studentId,
            String subjectId
    );

    Integer countByStudentStudentId(String studentId);
}