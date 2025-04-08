
package presentation;

import business.*;
import entity.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UniversityManager {
    public static List<Course> courseList = new ArrayList<>();
    public static List<Student> studentList = new ArrayList<>();
    public static List<Teacher> teacherList = new ArrayList<>();
    public static List<ClassRoom> classRoomList = new ArrayList<>();
    public static List<CourseRegistration> courseRegistrationList = new ArrayList<>();
    public static List<Schedule> scheduleList = new ArrayList<>();
    public static List<Person> personList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("************************* UNIVERSITY MENU **********************");
            System.out.println("1. Quản lý khóa học ");
            System.out.println("2. Quản lý giảng viên");
            System.out.println("3. Quản lý sinh viên");
            System.out.println("4. Đăng ký khóa học");
            System.out.println("5. Quản lý lớp học");
            System.out.println("6. Thống kê");
            System.out.println("7. Thoát");
            System.out.printf("Lựa chọn của bạn: ");

            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    CourseBusiness.printCourseMenu(sc);
                    break;
                case 2:
                    TeacherBusiness.displayTeacherMenu(sc);
                    break;
                case 3:
                    StudentBusiness.displayStudentMenu(sc);
                    break;
                case 4:
                    CourseRegistrationBusiness.displayCourseRegistrationMenu(sc);
                    break;
                case 5:
                    ClassRoomBusiness.displayClassRoomMenu(sc);
                    break;
                case 6:
                    Dashboard.printDashboardMenu(sc);
                    break;
                case 7:
                    System.exit(0);
                default:
                    System.err.println("Vui lòng chọn lại từ 1 - 7");
            }
        } while (true);
    }
}
