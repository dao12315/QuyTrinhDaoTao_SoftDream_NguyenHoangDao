package com;

import com.model.Diem;
import com.model.SinhVien;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final QuanLySV quanlysinhvien = new QuanLySVImpl();

    public static void main(String[] args) {
        while (true) {
            showMenu();

            System.out.print("Chọn chức năng: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> showAllStudents();
                case "2" -> showStudentDetail();
                case "3" -> showStudentSubjectCount();
                case "4" -> showStudentScores();
                case "5" -> updateStudentScore();
                case "6" -> showStudentResults();
                case "0" -> {
                    System.out.println("Thoát chương trình.");
                    return;
                }
                default -> System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }

            System.out.println();
            System.out.println("Nhấn Enter để tiếp tục...");
            scanner.nextLine();
        }
    }

    private static void showMenu() {
        System.out.println("======================================");
        System.out.println("        QUẢN LÝ ĐIỂM SINH VIÊN");
        System.out.println("======================================");
        System.out.println("1. Xem danh sách sinh viên");
        System.out.println("2. Xem chi tiết sinh viên");
        System.out.println("3. Xem số môn học sinh viên đăng ký");
        System.out.println("4. Xem điểm môn học của sinh viên");
        System.out.println("5. Nhập điểm của sinh viên");
        System.out.println("6. Xem kết quả trượt/đỗ của sinh viên");
        System.out.println("0. Thoát");
        System.out.println("======================================");
    }

    private static void showAllStudents() {
        List<SinhVien> students = quanlysinhvien.getAllStudents();

        if (students.isEmpty()) {
            System.out.println("Không có sinh viên nào.");
            return;
        }

        System.out.println("DANH SÁCH SINH VIÊN");
        System.out.printf(
                "%-10s %-25s %-10s %-15s %-15s %-10s%n",
                "Mã SV", "Tên sinh viên", "Giới tính", "Ngày sinh", "Lớp", "Khóa"
        );

        for (SinhVien sinhVien : students) {
            System.out.printf(
                    "%-10s %-25s %-10s %-15s %-15s %-10s%n",
                    sinhVien.getMaSinhVien(),
                    sinhVien.getTenSinhVien(),
                    sinhVien.getGioiTinh(),
                    sinhVien.getNgaySinh(),
                    sinhVien.getLop(),
                    sinhVien.getKhoa()
            );
        }
    }

    private static void showStudentDetail() {
        System.out.print("Nhập mã sinh viên: ");
        String studentId = scanner.nextLine().trim();

        SinhVien sinhVien = quanlysinhvien.getStudentById(studentId);

        if (sinhVien == null) {
            System.out.println("Không tìm thấy sinh viên có mã: " + studentId);
            return;
        }

        System.out.println("THÔNG TIN CHI TIẾT SINH VIÊN");
        System.out.println("Mã sinh viên : " + sinhVien.getMaSinhVien());
        System.out.println("Tên sinh viên: " + sinhVien.getTenSinhVien());
        System.out.println("Giới tính    : " + sinhVien.getGioiTinh());
        System.out.println("Ngày sinh    : " + sinhVien.getNgaySinh());
        System.out.println("Lớp          : " + sinhVien.getLop());
        System.out.println("Khóa         : " + sinhVien.getKhoa());
    }

    private static void showStudentSubjectCount() {
        System.out.print("Nhập mã sinh viên: ");
        String studentId = scanner.nextLine().trim();

        SinhVien sinhVien = quanlysinhvien.getStudentById(studentId);

        if (sinhVien == null) {
            System.out.println("Không tìm thấy sinh viên có mã: " + studentId);
            return;
        }

        int totalSubjects = quanlysinhvien.countSubjectsByStudentId(studentId);

        System.out.println("Sinh viên: " + sinhVien.getTenSinhVien());
        System.out.println("Số môn học đã đăng ký: " + totalSubjects);
    }

    private static void showStudentScores() {
        System.out.print("Nhập mã sinh viên: ");
        String studentId = scanner.nextLine().trim();

        List<Diem> scores = quanlysinhvien.getScoresByStudentId(studentId);

        if (scores.isEmpty()) {
            System.out.println("Không có dữ liệu điểm cho sinh viên có mã: " + studentId);
            return;
        }

        System.out.println("ĐIỂM MÔN HỌC CỦA SINH VIÊN");
        System.out.printf(
                "%-10s %-25s %-10s %-25s %-15s %-15s %-15s%n",
                "Mã SV", "Tên sinh viên", "Mã môn", "Tên môn",
                "Điểm QT", "Điểm TP", "Tổng kết"
        );

        for (Diem diem : scores) {
            System.out.printf(
                    "%-10s %-25s %-10s %-25s %-15s %-15s %-15s%n",
                    diem.getMaSinhVien(),
                    diem.getTenSinhVien(),
                    diem.getMaMonHoc(),
                    diem.getTenMonHoc(),
                    formatValue(diem.getDiemQuaTrinh()),
                    formatValue(diem.getDiemThanhPhan()),
                    formatValue(diem.getDiemTongKet())
            );
        }
    }

    private static void updateStudentScore() {
        System.out.print("Nhập mã sinh viên: ");
        String studentId = scanner.nextLine().trim();

        SinhVien sinhVien = quanlysinhvien.getStudentById(studentId);

        if (sinhVien == null) {
            System.out.println("Không tìm thấy sinh viên có mã: " + studentId);
            return;
        }

        System.out.print("Nhập mã môn học: ");
        String subjectId = scanner.nextLine().trim();

        BigDecimal processScore = inputScore("Nhập điểm quá trình: ");
        BigDecimal finalScore = inputScore("Nhập điểm thành phần: ");

        boolean success = quanlysinhvien.updateScore(
                studentId,
                subjectId,
                processScore,
                finalScore
        );

        if (success) {
            System.out.println("Nhập điểm thành công.");
        } else {
            System.out.println("Nhập điểm thất bại. Kiểm tra lại mã sinh viên hoặc mã môn học.");
        }
    }

    private static void showStudentResults() {
        System.out.print("Nhập mã sinh viên: ");
        String studentId = scanner.nextLine().trim();

        List<Diem> results = quanlysinhvien.getResultsByStudentId(studentId);

        if (results.isEmpty()) {
            System.out.println("Không có dữ liệu kết quả cho sinh viên có mã: " + studentId);
            return;
        }

        System.out.println("KẾT QUẢ TRƯỢT/ĐỖ CỦA SINH VIÊN");
        System.out.printf(
                "%-10s %-25s %-25s %-15s %-15s %-15s %-15s%n",
                "Mã SV", "Tên sinh viên", "Tên môn",
                "Điểm QT", "Điểm TP", "Tổng kết", "Kết quả"
        );

        for (Diem diem : results) {
            System.out.printf(
                    "%-10s %-25s %-25s %-15s %-15s %-15s %-15s%n",
                    diem.getMaSinhVien(),
                    diem.getTenSinhVien(),
                    diem.getTenMonHoc(),
                    formatValue(diem.getDiemQuaTrinh()),
                    formatValue(diem.getDiemThanhPhan()),
                    formatValue(diem.getDiemTongKet()),
                    diem.getKetQua()
            );
        }
    }

    private static BigDecimal inputScore(String message) {
        while (true) {
            try {
                System.out.print(message);
                String input = scanner.nextLine().trim();

                BigDecimal score = new BigDecimal(input);

                if (score.compareTo(BigDecimal.ZERO) < 0 || score.compareTo(BigDecimal.TEN) > 0) {
                    System.out.println("Điểm phải nằm trong khoảng từ 0 đến 10.");
                    continue;
                }

                return score;
            } catch (NumberFormatException e) {
                System.out.println("Điểm không hợp lệ. Vui lòng nhập số.");
            }
        }
    }

    private static String formatValue(BigDecimal value) {
        if (value == null) {
            return "Chua co";
        }

        return value.stripTrailingZeros().toPlainString();
    }
}