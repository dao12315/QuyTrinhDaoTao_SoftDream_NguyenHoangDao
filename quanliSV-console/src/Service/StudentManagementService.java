package Service;



import Model.Student;
import Model.StudentCourse;
import Model.Subject;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentManagementService {
    private List<Student> students = new ArrayList<>();
    private List<Subject> subjects = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public StudentManagementService() {
        initData();
    }

    private void initData() {
        /*
         * =========================
         * 1. SEED 5 MON HOC
         * =========================
         */
        Subject java = new Subject("MH001", "Lap trinh Java", 45, 0.4, 0.6);
        Subject sql = new Subject("MH002", "Co so du lieu SQL", 30, 0.3, 0.7);
        Subject web = new Subject("MH003", "Lap trinh Web", 45, 0.5, 0.5);
        Subject dsa = new Subject("MH004", "Cau truc du lieu", 45, 0.4, 0.6);
        Subject network = new Subject("MH005", "Mang may tinh", 30, 0.5, 0.5);

        subjects.add(java);
        subjects.add(sql);
        subjects.add(web);
        subjects.add(dsa);
        subjects.add(network);

        /*
         * =========================
         * 2. SEED 5 SINH VIEN
         * =========================
         */
        Student student1 = new Student(
                "SV001",
                "Nguyen Van An",
                "Nam",
                "12/05/2004",
                "73DCTT24",
                "K73"
        );

        Student student2 = new Student(
                "SV002",
                "Tran Thi Binh",
                "Nu",
                "20/08/2004",
                "73DCTT24",
                "K73"
        );

        Student student3 = new Student(
                "SV003",
                "Le Van Cuong",
                "Nam",
                "01/01/2004",
                "73DCTT25",
                "K73"
        );

        Student student4 = new Student(
                "SV004",
                "Pham Minh Duc",
                "Nam",
                "18/03/2004",
                "73DCTT25",
                "K73"
        );

        Student student5 = new Student(
                "SV005",
                "Hoang Thi Ha",
                "Nu",
                "09/11/2004",
                "73DCTT26",
                "K73"
        );

        /*
         * =========================
         * 3. SEED MON HOC DANG KY + DIEM CHO SV001
         * =========================
         */
        StudentCourse s1Java = new StudentCourse(java);
        s1Java.setProcessScore(7.0);
        s1Java.setFinalScore(8.0);

        StudentCourse s1Sql = new StudentCourse(sql);
        s1Sql.setProcessScore(5.5);
        s1Sql.setFinalScore(6.0);

        StudentCourse s1Web = new StudentCourse(web);
        // Chua co diem de test chuc nang nhap diem

        student1.addCourse(s1Java);
        student1.addCourse(s1Sql);
        student1.addCourse(s1Web);

        /*
         * =========================
         * 4. SEED MON HOC DANG KY + DIEM CHO SV002
         * =========================
         */
        StudentCourse s2Java = new StudentCourse(java);
        s2Java.setProcessScore(3.0);
        s2Java.setFinalScore(4.0);

        StudentCourse s2Dsa = new StudentCourse(dsa);
        s2Dsa.setProcessScore(8.0);
        s2Dsa.setFinalScore(7.5);

        StudentCourse s2Network = new StudentCourse(network);
        // Chua co diem

        student2.addCourse(s2Java);
        student2.addCourse(s2Dsa);
        student2.addCourse(s2Network);

        /*
         * =========================
         * 5. SEED MON HOC DANG KY + DIEM CHO SV003
         * =========================
         */
        StudentCourse s3Sql = new StudentCourse(sql);
        s3Sql.setProcessScore(6.0);
        s3Sql.setFinalScore(7.0);

        StudentCourse s3Network = new StudentCourse(network);
        // Chua co diem

        StudentCourse s3Web = new StudentCourse(web);
        s3Web.setProcessScore(4.0);
        s3Web.setFinalScore(3.5);

        student3.addCourse(s3Sql);
        student3.addCourse(s3Network);
        student3.addCourse(s3Web);

        /*
         * =========================
         * 6. SEED MON HOC DANG KY + DIEM CHO SV004
         * =========================
         */
        StudentCourse s4Java = new StudentCourse(java);
        s4Java.setProcessScore(2.5);
        s4Java.setFinalScore(3.0);

        StudentCourse s4Web = new StudentCourse(web);
        s4Web.setProcessScore(7.0);
        s4Web.setFinalScore(6.5);

        StudentCourse s4Network = new StudentCourse(network);
        s4Network.setProcessScore(5.0);
        s4Network.setFinalScore(5.5);

        student4.addCourse(s4Java);
        student4.addCourse(s4Web);
        student4.addCourse(s4Network);

        /*
         * =========================
         * 7. SEED MON HOC DANG KY + DIEM CHO SV005
         * =========================
         */
        StudentCourse s5Sql = new StudentCourse(sql);
        s5Sql.setProcessScore(9.0);
        s5Sql.setFinalScore(8.5);

        StudentCourse s5Dsa = new StudentCourse(dsa);
        // Chua co diem

        StudentCourse s5Web = new StudentCourse(web);
        s5Web.setProcessScore(4.0);
        s5Web.setFinalScore(4.0);

        student5.addCourse(s5Sql);
        student5.addCourse(s5Dsa);
        student5.addCourse(s5Web);

        /*
         * =========================
         * 8. THEM SINH VIEN VAO DANH SACH CHINH
         * =========================
         */
        students.add(student1);
        students.add(student2);
        students.add(student3);
        students.add(student4);
        students.add(student5);
    }

    public void showStudentList() {
        System.out.println("\n===== DANH SACH SINH VIEN =====");

        System.out.printf("%-10s %-25s %-10s %-15s %-12s %-10s%n",
                "Ma SV", "Ho ten", "Gioi tinh", "Ngay sinh", "Lop", "Khoa");

        for (Student student : students) {
            System.out.printf("%-10s %-25s %-10s %-15s %-12s %-10s%n",
                    student.getStudentId(),
                    student.getFullName(),
                    student.getGender(),
                    student.getDateOfBirth(),
                    student.getClassName(),
                    student.getCourse());
        }
    }

    public void showStudentDetail() {
        System.out.print("Nhap ma sinh vien: ");
        String studentId = scanner.nextLine().trim();

        Student student = findStudentById(studentId);

        if (student == null) {
            System.out.println("Khong tim thay sinh vien.");
            return;
        }

        System.out.println("\n===== CHI TIET SINH VIEN =====");
        System.out.println("Ma sinh vien: " + student.getStudentId());
        System.out.println("Ho ten: " + student.getFullName());
        System.out.println("Gioi tinh: " + student.getGender());
        System.out.println("Ngay sinh: " + student.getDateOfBirth());
        System.out.println("Lop: " + student.getClassName());
        System.out.println("Khoa: " + student.getCourse());

        System.out.println("\nDanh sach mon hoc da dang ky:");
        for (StudentCourse studentCourse : student.getRegisteredCourses()) {
            Subject subject = studentCourse.getSubject();
            System.out.println("- " + subject.getSubjectId() + " - " + subject.getSubjectName());
        }
    }

    public void showNumberOfRegisteredSubjects() {
        System.out.print("Nhap ma sinh vien: ");
        String studentId = scanner.nextLine().trim();

        Student student = findStudentById(studentId);

        if (student == null) {
            System.out.println("Khong tim thay sinh vien.");
            return;
        }

        int total = student.getRegisteredCourses().size();

        System.out.println("Sinh vien " + student.getFullName()
                + " da dang ky " + total + " mon hoc.");
    }

    public void showScoresByStudent() {
        System.out.print("Nhap ma sinh vien: ");
        String studentId = scanner.nextLine().trim();

        Student student = findStudentById(studentId);

        if (student == null) {
            System.out.println("Khong tim thay sinh vien.");
            return;
        }

        System.out.println("\n===== DIEM MON HOC CUA SINH VIEN =====");
        System.out.println("Sinh vien: " + student.getFullName());

        System.out.printf("%-25s %-15s %-18s %-15s%n",
                "Mon hoc", "Qua trinh", "Thanh phan", "Tong ket");

        for (StudentCourse studentCourse : student.getRegisteredCourses()) {
            if (studentCourse.hasScore()) {
                System.out.printf("%-25s %-15.2f %-18.2f %-15.2f%n",
                        studentCourse.getSubject().getSubjectName(),
                        studentCourse.getProcessScore(),
                        studentCourse.getFinalScore(),
                        studentCourse.calculateTotalScore());
            } else {
                System.out.printf("%-25s %-15s %-18s %-15s%n",
                        studentCourse.getSubject().getSubjectName(),
                        "Chua co",
                        "Chua co",
                        "Chua co");
            }
        }
    }

    public void inputScore() {
        System.out.print("Nhap ma sinh vien: ");
        String studentId = scanner.nextLine().trim();

        Student student = findStudentById(studentId);

        if (student == null) {
            System.out.println("Khong tim thay sinh vien.");
            return;
        }

        System.out.println("\nDanh sach mon hoc sinh vien da dang ky:");
        for (StudentCourse studentCourse : student.getRegisteredCourses()) {
            Subject subject = studentCourse.getSubject();
            System.out.println(subject.getSubjectId() + " - " + subject.getSubjectName());
        }

        System.out.print("Nhap ma mon hoc: ");
        String subjectId = scanner.nextLine().trim();

        StudentCourse studentCourse = findStudentCourse(student, subjectId);

        if (studentCourse == null) {
            System.out.println("Sinh vien khong dang ky mon hoc nay.");
            return;
        }

        double processScore = inputScoreValue("Nhap diem qua trinh: ");
        double finalScore = inputScoreValue("Nhap diem thanh phan: ");

        studentCourse.setProcessScore(processScore);
        studentCourse.setFinalScore(finalScore);

        System.out.println("Nhap diem thanh cong.");
    }

    public void showPassFailResult() {
        System.out.print("Nhap ma sinh vien: ");
        String studentId = scanner.nextLine().trim();

        Student student = findStudentById(studentId);

        if (student == null) {
            System.out.println("Khong tim thay sinh vien.");
            return;
        }

        System.out.println("\n===== KET QUA TRUOT / DO =====");
        System.out.println("Sinh vien: " + student.getFullName());

        System.out.printf("%-25s %-15s %-15s%n",
                "Mon hoc", "Tong ket", "Ket qua");

        for (StudentCourse studentCourse : student.getRegisteredCourses()) {
            if (studentCourse.hasScore()) {
                System.out.printf("%-25s %-15.2f %-15s%n",
                        studentCourse.getSubject().getSubjectName(),
                        studentCourse.calculateTotalScore(),
                        studentCourse.getResult());
            } else {
                System.out.printf("%-25s %-15s %-15s%n",
                        studentCourse.getSubject().getSubjectName(),
                        "Chua co",
                        "Chua co diem");
            }
        }
    }

    public void showSubjectList() {
        System.out.println("\n===== DANH SACH MON HOC =====");

        System.out.printf("%-10s %-25s %-12s %-15s %-15s%n",
                "Ma MH", "Ten mon hoc", "So tiet", "Ty le QT", "Ty le TP");

        for (Subject subject : subjects) {
            System.out.printf("%-10s %-25s %-12d %-15.0f %-15.0f%n",
                    subject.getSubjectId(),
                    subject.getSubjectName(),
                    subject.getNumberOfLessons(),
                    subject.getProcessRate() * 100,
                    subject.getFinalRate() * 100);
        }
    }

    private Student findStudentById(String studentId) {
        for (Student student : students) {
            if (student.getStudentId().equalsIgnoreCase(studentId)) {
                return student;
            }
        }

        return null;
    }

    private StudentCourse findStudentCourse(Student student, String subjectId) {
        for (StudentCourse studentCourse : student.getRegisteredCourses()) {
            if (studentCourse.getSubject().getSubjectId().equalsIgnoreCase(subjectId)) {
                return studentCourse;
            }
        }

        return null;
    }

    private double inputScoreValue(String message) {
        while (true) {
            try {
                System.out.print(message);
                double score = Double.parseDouble(scanner.nextLine());

                if (score < 0 || score > 10) {
                    System.out.println("Diem phai nam trong khoang tu 0 den 10.");
                } else {
                    return score;
                }
            } catch (NumberFormatException e) {
                System.out.println("Vui long nhap so hop le.");
            }
        }
    }
}