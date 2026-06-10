package com.example.qlsv_springboot.repository;

import com.example.qlsv_springboot.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, String> {
    Optional<Student> findByUserId(Integer userId);
}