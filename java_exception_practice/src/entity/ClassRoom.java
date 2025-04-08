package entity;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class ClassRoom implements IApp{
    public static final int MAX_STUDENTS = 5;
    public static int count = 0;
    private int classroomId;
    private String classroomName;
    private String courseId;
    private int teacherId;
    private List<Student> students;
    private LocalDate createdAt;
    public enum Status{
        PENDING, PROGRESS, CLOSE
    }

    private Status status;

    public ClassRoom() {
        this.classroomId = ++count;
        this.students = new ArrayList<>();
    }

    public ClassRoom(String classroomName, String courseId, int teacherId, List<Student> students, LocalDate createdAt) {
        this.classroomName = classroomName;
        this.courseId = courseId;
        this.teacherId = teacherId;
        this.students = students;
        this.createdAt = createdAt;
    }

    public int getClassroomId() {
        return classroomId;
    }

    public String getClassroomName() {
        return classroomName;
    }

    public void setClassroomName(String classroomName) {
        this.classroomName = classroomName;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public void inputData(Scanner sc) {
        this.classroomName = sc.nextLine();

        this.courseId = sc.nextLine();

        this.createdAt = LocalDate.now() ;

        this.status = Status.valueOf(sc.nextLine());
    }



    public String displayStudents() {
        if (students.isEmpty()) {
            return "Lớp chưa có sinh viên!";
        }

        StringBuilder studentList = new StringBuilder("\nDanh sách sinh viên trong lớp " + classroomName + ":\n");
        students.forEach(student -> {
            studentList.append(student.toString()).append("\n--------------------------------\n");
        });

        return studentList.toString();
    }

    @Override
    public String toString() {
        return "ClassRoomId: " + classroomId +
                "\nClassroomName: " + classroomName +
                "\nCourseId: " + courseId +
                "\nTeacherId: " + (teacherId == 0 ? "Chưa có" : teacherId) + "\n" +
                displayStudents() +
                "\nCreatedAt: " + createdAt +
                "\nStatus: " + status +
                "\n-------------------------------\n";
    }
}