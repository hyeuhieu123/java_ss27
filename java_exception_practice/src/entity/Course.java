package entity;



import java.util.Scanner;

public class Course implements IApp {
    private String courseId;
    private String courseName;
    private boolean status;

    public Course() {
    }

    public Course(String courseId, String courseName, boolean status) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.status = status;
    }

    public String getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public void inputData(Scanner sc) {
        this.courseId = sc.nextLine();

        this.courseName = sc.nextLine();

        this.status = Boolean.parseBoolean(sc.nextLine());
    }





    public void displayData() {
        System.out.printf("Course ID: %s\n", courseId);
        System.out.printf("Course Name: %s\n", courseName);
        System.out.printf("Status: %s\n", status);
    }
}