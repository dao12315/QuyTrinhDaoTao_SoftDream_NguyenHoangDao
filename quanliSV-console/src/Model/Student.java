package Model;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private String studentId;
    private String fullName;
    private String gender;
    private String dateOfBirth;
    private String className;
    private String course;
    private List<StudentCourse> registeredCourses;

    public Student(String studentId, String fullName, String gender,
                   String dateOfBirth, String className, String course) {
        this.studentId = studentId;
        this.fullName = fullName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.className = className;
        this.course = course;
        this.registeredCourses = new ArrayList<>();
    }

    public String getStudentId() {
        return studentId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getGender() {
        return gender;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getClassName() {
        return className;
    }

    public String getCourse() {
        return course;
    }

    public List<StudentCourse> getRegisteredCourses() {
        return registeredCourses;
    }

    public void addCourse(StudentCourse studentCourse) {
        registeredCourses.add(studentCourse);
    }
}