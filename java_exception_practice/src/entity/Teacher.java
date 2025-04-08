package entity;



import java.util.Scanner;

public class Teacher extends Person implements IApp{
    public static int count = 0;
    private int teacherId;
    private String subject;

    public Teacher(String name, int age, String address, String phoneNumber, String email, Gender gender) {
        super(name, age, address, phoneNumber, email, gender);
        this.teacherId = ++count;
    }

    public Teacher(String name, int age, String address, String phoneNumber, String email, Gender gender, String subject) {
        super(name, age, address, phoneNumber, email, gender);
        this.teacherId = ++count;
        this.subject = subject;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public void inputData(Scanner sc) {
        super.inputData(sc);
        this.subject = sc.nextLine();
    }

    @Override
    public String toString() {
        return "TeacherId: " + teacherId + super.toString() + ", Subject: " + subject;
    }
}