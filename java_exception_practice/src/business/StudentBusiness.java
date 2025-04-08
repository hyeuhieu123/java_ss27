package business;

import entity.CourseRegistration;
import entity.Person;
import entity.Student;
import presentation.UniversityManager;
import validate.PersonValidator;
import validate.StringRule;
import validate.Validator;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class StudentBusiness {
    public static void displayStudentMenu(Scanner sc) {
        StudentBusiness studentBusiness = new StudentBusiness();
        do {
            System.out.println("************************STUDENT MENU***************************");
            System.out.println("1. Danh sách sinh viên được sắp xếp theo tên tăng dần");
            System.out.println("2. Thêm mới sinh viên");
            System.out.println("3. Cập nhật sinh viên");
            System.out.println("4. Xóa sinh viên (chỉ xóa nếu sinh viên chưa đăng ký khóa học nào)");
            System.out.println("5. Trở về menu chính");
            System.out.printf("Lựa chọn của bạn: ");

            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    studentBusiness.displayStudents();
                    break;
                case 2:
                    studentBusiness.addStudent(sc);
                    break;
                case 3:
                    studentBusiness.updateStudent(sc);
                    break;
                case 4:
                    studentBusiness.deleteStudent(sc);
                    break;
                case 5:
                    return;
                default:
                    System.err.println("Vui lòng chọn lại từ 1 - 5!");
            }
        } while (true);
    }

    public void displayStudents(){
        if (UniversityManager.studentList.isEmpty()) {
            System.err.println("Danh sách sinh viên trống!");
            return;
        }

        UniversityManager.studentList.stream()
                .sorted(Comparator.comparing(Student::getName))
                .forEach(System.out::println);
    }

    public void addStudent(Scanner sc) {
        System.out.println("Nhập số sinh viên cần thêm: ");
        int size = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < size; i++) {
            System.out.println("Nhập thông tin sinh viên thứ " + (i + 1) + ": ");
            Student student = new Student("", 0, "", "", "", Person.Gender.OTHER);
            student.inputData(sc);
            UniversityManager.studentList.add(student);
            System.out.println("Thêm sinh viên thành công!");
        }
    }

    public void updateStudent(Scanner sc) {
        System.out.println("Nhập vào id sinh viên cần cập nhật:");
        String studentId = sc.nextLine();

        int indexUpdate = findStudentById(studentId);

        if (indexUpdate == -1) {
            System.err.println("Không tìm thấy sinh viên!");
            return;
        }

        do {
            System.out.println("---------------CẬP NHẬT SINH VIÊN----------------");
            System.out.println("1. Cập nhật tên sinh viên");
            System.out.println("2. Cập nhật tuổi sinh viên");
            System.out.println("3. Cập nhật địa chỉ sinh viên");
            System.out.println("4. Cập nhật số điện thoại sinh viên");
            System.out.println("5. Cập nhật email sinh viên");
            System.out.println("6. Cập nhật giới tính sinh viên");
            System.out.println("7. Cập nhật điểm trung bình");
            System.out.println("8. Trở về menu quản lí sinh viên");
            System.out.printf("Lựa chọn của bạn: ");

            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    String teacherName = Validator.validateInputString(sc, "Nhập tên mới: ", new StringRule(0, 150));
                    UniversityManager.studentList.get(indexUpdate).setName(teacherName);
                    System.out.println("Cập nhật tên thành công!");
                    break;
                case 2:
                    int newAge = PersonValidator.validateInputAge(sc, "Nhập tuổi mới: ", 18);
                    UniversityManager.studentList.get(indexUpdate).setAge(newAge);
                    System.out.println("Cập nhật tuổi thành công!");
                    break;
                case 3:
                    String newAddress = Validator.validateInputString(sc, "Nhập địa chỉ mới: ", new StringRule());
                    UniversityManager.studentList.get(indexUpdate).setAddress(newAddress);
                    System.out.println("Cập nhật địa chỉ thành công!");
                    break;
                case 4:
                    String newPhone = PersonValidator.validateInputPhone(sc, "Nhập số điện thoại: ", "^(0\\d{9}|\\+84\\d{9})$");
                    UniversityManager.studentList.get(indexUpdate).setPhoneNumber(PersonValidator.validateExistPerson(sc, newPhone, "phoneNumber"));
                    System.out.println("Cập nhật số điện thoại thành công!");
                    break;
                case 5:
                    String newEmail = PersonValidator.validateInputEmail(sc, "Nhập email: ", "^[a-zA-Z0-9._]+@[a-zA-Z0-9]+\\.[a-zA-Z]{2,6}$");
                    UniversityManager.studentList.get(indexUpdate).setEmail(PersonValidator.validateExistPerson(sc, newEmail, "email"));
                    System.out.println("Cập nhật email thành công!");
                    break;
                case 6:
                    Person.Gender newGender = Validator.validateEnumInput(sc, "Nhập giới tính mới: ", Person.Gender.class);
                    UniversityManager.studentList.get(indexUpdate).setGender(newGender);
                    System.out.println("Cập nhật giới tính thành công!");
                    break;
                case 7:
                    double newGpa = Validator.validateInputDouble(sc, "Nhập điểm trung bình mới: ");
                    UniversityManager.studentList.get(indexUpdate).setGpa(newGpa);
                    System.out.println("Cập nhật điểm trung bình thành công!");
                    break;
                case 8:
                    return;
                default:
                    System.err.println("Vui lòng chọn lại từ 1 - 8!");
            }
        } while (true);
    }

    public void deleteStudent(Scanner sc) {
        System.out.println("Nhập vào 1 id sinh viên cần xóa:");
        String studentId = sc.nextLine();

        int indexDelete = findStudentById(studentId);

        if (indexDelete == -1) {
            System.err.println("Không tìm thấy sinh viên!");
            return;
        }

        List<CourseRegistration> filterStudentById = UniversityManager.courseRegistrationList.stream()
                .filter(courseRegistration -> courseRegistration.getStudentId().equals(studentId))
                .toList();

        if(!filterStudentById.isEmpty()) {
            System.err.println("Sinh viên có đăng ký khóa học. Không thể xóa!");
            return;
        }

        System.out.println("Bạn có xác nhận xóa sinh viên này không?");
        String confirm = sc.nextLine();

        if (confirm.equals("y")) {
            UniversityManager.studentList.remove(indexDelete);
            System.out.println("Xóa sinh viên thành công");
        }else if(confirm.equals("n")) {
            System.out.println("Đã hủy xác nhận xóa!");
        }
    }

    public int findStudentById(String id) {
        for (int i = 0; i < UniversityManager.studentList.size(); i++) {
            if(UniversityManager.studentList.get(i).getStudentId().equals(id)) {
                return i;
            }
        }
        return -1;
    }
}