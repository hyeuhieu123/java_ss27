package entity;



import java.util.Scanner;

public class Person implements IApp {
    private String name;
    private int age;
    private String address;
    private String phoneNumber;
    private String email;
    public enum Gender {
        MALE, FEMALE, OTHER
    }
    private Gender gender;

    public Person(String name, int age, String address, String phoneNumber, String email) {
    }

    public Person(String name, int age, String address, String phoneNumber, String email, Gender gender) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public void inputData(Scanner sc) {
        this.name = sc.nextLine();

        this.age = Integer.parseInt(sc.nextLine());

        this.address = sc.nextLine();

        this.phoneNumber = sc.nextLine();

        this.email = sc.nextLine();

        this.gender = Gender.valueOf(sc.nextLine());
    }





    @Override
    public String toString() {
        return ", Name: " + name +
                ", Age: " + age +
                ", Address: " + address +
                ", Phone: " + phoneNumber +
                ", Email: " + email +
                ", Gender: " + displayGender();
    }

    public String displayGender(){
        if (gender == null) {
            return "Không xác định";
        }

        switch (gender) {
            case MALE:
                return "Nam";
            case FEMALE:
                return "Nữ";
            case OTHER:
                return "Khác";
            default:
                return "Không xác định";
        }
    }
}