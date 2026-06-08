
import Service.StudentManagementService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StudentManagementService service = new StudentManagementService();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            showMenu();

            int choice;

            try {
                System.out.print("Nhap lua chon: ");
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Lua chon khong hop le. Vui long nhap so.");
                continue;
            }

            switch (choice) {
                case 1:
                    service.showStudentList();
                    break;
                case 2:
                    service.showStudentDetail();
                    break;
                case 3:
                    service.showNumberOfRegisteredSubjects();
                    break;
                case 4:
                    service.showScoresByStudent();
                    break;
                case 5:
                    service.inputScore();
                    break;
                case 6:
                    service.showPassFailResult();
                    break;
                case 7:
                    service.showSubjectList();
                    break;
                case 0:
                    System.out.println("Thoat chuong trinh.");
                    return;
                default:
                    System.out.println("Lua chon khong hop le.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("\n========== QUAN LY DIEM SINH VIEN ==========");
        System.out.println("1. Xem danh sach sinh vien");
        System.out.println("2. Xem chi tiet sinh vien");
        System.out.println("3. Xem so mon hoc sinh vien dang ky");
        System.out.println("4. Xem diem mon hoc cua sinh vien");
        System.out.println("5. Nhap diem cua sinh vien");
        System.out.println("6. Xem ket qua truot/do cua sinh vien");
        System.out.println("7. Xem danh sach mon hoc");
        System.out.println("0. Thoat");
    }
}