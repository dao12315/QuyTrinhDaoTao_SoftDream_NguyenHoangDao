package com;




import com.config.DBconfig;
import com.model.Diem;
import com.model.SinhVien;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuanLySVImpl implements QuanLySV {

    @Override
    public List<SinhVien> getAllStudents() {
        List<SinhVien> students = new ArrayList<>();

        String sql = """
                SELECT 
                    student_id,
                    full_name,
                    gender,
                    date_of_birth,
                    class_name,
                    course
                FROM students
                ORDER BY student_id
                """;

        try (
                Connection connection = DBconfig.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()
        ) {
            while (resultSet.next()) {
                SinhVien sinhVien = new SinhVien(
                        resultSet.getString("student_id"),
                        resultSet.getString("full_name"),
                        resultSet.getString("gender"),
                        resultSet.getDate("date_of_birth").toLocalDate(),
                        resultSet.getString("class_name"),
                        resultSet.getString("course")
                );

                students.add(sinhVien);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi lấy danh sách sinh viên: " + e.getMessage());
        }

        return students;
    }

    @Override
    public SinhVien getStudentById(String studentId) {
        String sql = """
                SELECT 
                    student_id,
                    full_name,
                    gender,
                    date_of_birth,
                    class_name,
                    course
                FROM students
                WHERE student_id = ?
                """;

        try (
                Connection connection = DBconfig.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setString(1, studentId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new SinhVien(
                            resultSet.getString("student_id"),
                            resultSet.getString("full_name"),
                            resultSet.getString("gender"),
                            resultSet.getDate("date_of_birth").toLocalDate(),
                            resultSet.getString("class_name"),
                            resultSet.getString("course")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi lấy chi tiết sinh viên: " + e.getMessage());
        }

        return null;
    }

    @Override
    public int countSubjectsByStudentId(String studentId) {
        String sql = """
                SELECT COUNT(subject_id) AS total_subjects
                FROM student_subjects
                WHERE student_id = ?
                """;

        try (
                Connection connection = DBconfig.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setString(1, studentId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("total_subjects");
                }
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi đếm số môn học sinh viên đăng ký: " + e.getMessage());
        }

        return 0;
    }

    @Override
    public List<Diem> getScoresByStudentId(String studentId) {
        List<Diem> scores = new ArrayList<>();

        String sql = """
                SELECT 
                    s.student_id,
                    s.full_name,
                    sub.subject_id,
                    sub.subject_name,
                    ss.process_score,
                    ss.final_score,
                    ROUND(
                        ss.process_score * sub.process_rate
                        + ss.final_score * sub.final_rate,
                        2
                    ) AS total_score
                FROM students s
                JOIN student_subjects ss
                    ON s.student_id = ss.student_id
                JOIN subjects sub
                    ON ss.subject_id = sub.subject_id
                WHERE s.student_id = ?
                ORDER BY sub.subject_id
                """;

        try (
                Connection connection = DBconfig.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setString(1, studentId);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Diem diem = new Diem(
                            resultSet.getString("student_id"),
                            resultSet.getString("full_name"),
                            resultSet.getString("subject_id"),
                            resultSet.getString("subject_name"),
                            resultSet.getBigDecimal("process_score"),
                            resultSet.getBigDecimal("final_score"),
                            resultSet.getBigDecimal("total_score"),
                            null
                    );

                    scores.add(diem);
                }
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi lấy điểm môn học của sinh viên: " + e.getMessage());
        }

        return scores;
    }

    @Override
    public boolean updateScore(String studentId, String subjectId, BigDecimal processScore, BigDecimal finalScore) {
        String sql = """
                UPDATE student_subjects
                SET 
                    process_score = ?,
                    final_score = ?
                WHERE student_id = ?
                  AND subject_id = ?
                """;

        try (
                Connection connection = DBconfig.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setBigDecimal(1, processScore);
            statement.setBigDecimal(2, finalScore);
            statement.setString(3, studentId);
            statement.setString(4, subjectId);

            int affectedRows = statement.executeUpdate();

            return affectedRows > 0;
        } catch (SQLException e) {
            System.out.println("Lỗi khi nhập điểm sinh viên: " + e.getMessage());
        }

        return false;
    }

    @Override
    public List<Diem> getResultsByStudentId(String studentId) {
        List<Diem> results = new ArrayList<>();

        String sql = """
                SELECT 
                    s.student_id,
                    s.full_name,
                    sub.subject_id,
                    sub.subject_name,
                    ss.process_score,
                    ss.final_score,
                    ROUND(
                        ss.process_score * sub.process_rate
                        + ss.final_score * sub.final_rate,
                        2
                    ) AS total_score,
                    CASE
                        WHEN ss.process_score IS NULL 
                             OR ss.final_score IS NULL 
                            THEN 'Chua co diem'
                        WHEN ss.process_score * sub.process_rate 
                             + ss.final_score * sub.final_rate >= 4 
                            THEN 'Qua mon'
                        ELSE 'Truot mon'
                    END AS result
                FROM students s
                JOIN student_subjects ss
                    ON s.student_id = ss.student_id
                JOIN subjects sub
                    ON ss.subject_id = sub.subject_id
                WHERE s.student_id = ?
                ORDER BY sub.subject_id
                """;

        try (
                Connection connection = DBconfig.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setString(1, studentId);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Diem diem = new Diem(
                            resultSet.getString("student_id"),
                            resultSet.getString("full_name"),
                            resultSet.getString("subject_id"),
                            resultSet.getString("subject_name"),
                            resultSet.getBigDecimal("process_score"),
                            resultSet.getBigDecimal("final_score"),
                            resultSet.getBigDecimal("total_score"),
                            resultSet.getString("result")
                    );

                    results.add(diem);
                }
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi xem kết quả trượt/đỗ của sinh viên: " + e.getMessage());
        }

        return results;
    }
}