package business;

import entity.ClassRoom;
import entity.Course;
import presentation.UniversityManager;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class CourseBusiness {
    public static void printCourseMenu(Scanner sc) {
        CourseBusiness courseBusiness = new CourseBusiness();
        do {
            System.out.println("************************* COURSE MENU *************************");
            System.out.println("1. Danh sách khóa học sắp xếp theo tên tăng dần");
            System.out.println("2. Thêm mới khóa học");
            System.out.println("3. Cập nhật khóa học");
            System.out.println("4. Xóa khóa học theo ID (Chỉ xóa được khóa học nếu khóa học chưa có lớp học)");
            System.out.println("5. Trở về menu chính");
            System.out.printf("Lựa chọn của bạn: ");

            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    courseBusiness.displayCourses();
                    break;
                case 2:
                    courseBusiness.addCourse(sc);
                    break;
                case 3:
                    courseBusiness.updateCourse(sc);
                    break;
                case 4:
                    courseBusiness.deleteCourse(sc);
                    break;
                case 5:
                    return;
                default:
                    System.err.println("Vui lòng chọn lại từ 1 - 5");
            }
        } while (true);
    }

    public void displayCourses() {
        if (UniversityManager.courseList.isEmpty()) {
            System.err.println("Danh sách khóa học trống!");
            return;
        }

        UniversityManager.courseList.stream()
                .sorted(Comparator.comparing(Course::getCourseName))
                .forEach(course -> {
                    course.displayData();
                    System.out.println("-----------------------------");
                });
    }

    public void addCourse(Scanner sc) {
        System.out.println("Nhập vào số khóa học cần thêm:");
        int size = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < size; i++) {
            System.out.println("Nhập thông tin khóa học thứ " + (i + 1));
            Course course = new Course();
            course.inputData(sc);
            UniversityManager.courseList.add(course);
            System.out.println("Thêm khóa học thành công!");
        }
    }

    public void updateCourse(Scanner sc) {
        System.out.println("Nhập vào 1 id khóa học cần cập nhật:");
        String courseId = sc.nextLine();

        int indexUpdate = findCourseById(courseId);

        if (indexUpdate == -1) {
            System.err.println("Không tìm thấy khóa học!");
            return;
        }

        do {
            System.out.println("------------------CẬP NHẬT KHÓA HỌC------------------");
            System.out.println("1. Cập nhật tên khóa học");
            System.out.println("2. Cập nhật trạng thái");
            System.out.println("3. Trở về menu quản lí khóa học");
            System.out.printf("Lựa chọn của bạn: ");

            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    String courseName = sc.nextLine();
                    UniversityManager.courseList.get(indexUpdate).setCourseName(courseName);
                    System.out.println("Cập nhật tên khóa học thành công");
                    break;
                case 2:
                    boolean newStatus = Boolean.parseBoolean(sc.nextLine());
                    UniversityManager.courseList.get(indexUpdate).setStatus(newStatus);
                    System.out.println("Cập nhật trạng thái thành công!");
                    break;
                case 3:
                    return;
                default:
                    System.err.println("Vui lòng chọn lại từ 1 - 3");
            }
        } while (true);
    }

    public void deleteCourse(Scanner sc) {
        System.out.println("Nhập vào 1 id khóa hoc cần xóa:");
        String courseId = sc.nextLine();

        int indexDelete = findCourseById(courseId);

        if (indexDelete == -1) {
            System.err.println("Không tìm thấy khóa học!");
            return;
        }

        List<ClassRoom> filterCourseId = UniversityManager.classRoomList.stream()
                .filter(classRoom -> classRoom.getCourseId().equals(courseId))
                .toList();

        if(!filterCourseId.isEmpty()) {
            System.err.println("Khóa học đang có lớp học. Không thể xóa!");
            return;
        }

        System.out.println("Bạn có xác nhận xóa khóa học này không?");
        String confirm = sc.nextLine();

        if (confirm.equals("y")) {
            UniversityManager.courseList.remove(indexDelete);
            System.out.println("Xóa khóa học thành công");
        }else if(confirm.equals("n")) {
            System.out.println("Đã hủy xác nhận xóa!");
        }
    }

    public int findCourseById(String id) {
        for (int i = 0; i < UniversityManager.courseList.size(); i++) {
            if (UniversityManager.courseList.get(i).getCourseId().equals(id)) {
                return i;
            }
        }
        return -1;
    }
}