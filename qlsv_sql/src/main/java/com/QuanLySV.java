package com;


import com.model.Diem;
import com.model.SinhVien;

import java.math.BigDecimal;
import java.util.List;

public interface QuanLySV {

    List<SinhVien> getAllStudents();

    SinhVien getStudentById(String studentId);

    int countSubjectsByStudentId(String studentId);

    List<Diem> getScoresByStudentId(String studentId);

    boolean updateScore(String studentId, String subjectId, BigDecimal processScore, BigDecimal finalScore);

    List<Diem> getResultsByStudentId(String studentId);
}