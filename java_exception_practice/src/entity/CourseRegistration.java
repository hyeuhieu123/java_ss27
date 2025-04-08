package entity;

import presentation.UniversityManager;


import java.time.LocalDate;
import java.util.Scanner;

public class CourseRegistration implements IApp{
    public static int count = 0;
    private int crId;
    private String studentId;
    private String courseId;
    private LocalDate registerDate;
    public enum Status{
        PENDING, ENROLLED, DROPPED
    }

    private Status status;

    public CourseRegistration() {
        this.crId = ++count;
    }

    public CourseRegistration(String studentId, String courseId) {
        this.crId = ++count;
        this.studentId = studentId;
        this.courseId = courseId;
        this.registerDate = LocalDate.now();
    }

    public int getCrId() {
        return crId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public LocalDate getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(LocalDate registerDate) {
        this.registerDate = registerDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public void inputData(Scanner sc) {
        this.studentId = sc.nextLine();

        this.courseId = sc.nextLine();

        this.status =Status.valueOf(sc.nextLine());
    }

    @Override
    public String toString() {
        return "Id: " + crId + ", StudentId: " + studentId + ", CourseId: " + courseId + ", Status: " + status;
    }
}