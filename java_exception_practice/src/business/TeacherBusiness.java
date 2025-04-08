package business;

import entity.ClassRoom;
import entity.Person;
import entity.Teacher;
import presentation.UniversityManager;
import validate.PersonValidator;
import validate.StringRule;
import validate.Validator;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class TeacherBusiness {
    public static void displayTeacherMenu(Scanner sc) {
        TeacherBusiness teacherBusiness = new TeacherBusiness();
        do {
            System.out.println("*************************TEACHER MENU**************************");
            System.out.println("1. Danh sách giảng viên sắp xếp theo mã giảm dần");
            System.out.println("2. Thêm mới giảng viên");
            System.out.println("3. Cập nhật giảng viên");
            System.out.println("4. Xóa giảng viên (chỉ xóa được nếu giảng viên chưa được xếp lớp)");
            System.out.println("5. Trở về menu chính");
            System.out.printf("Lựa chọn của bạn: ");

            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    teacherBusiness.displayTeachers();
                    break;
                case 2:
                    teacherBusiness.addTeacher(sc);
                    break;
                case 3:
                    teacherBusiness.updateTeacher(sc);
                    break;
                case 4:
                    teacherBusiness.deleteTeacher(sc);
                    break;
                case 5:
                    return;
                default:
                    System.err.println("Vui lòng chọn lại từ 1 - 5!");
            }
        } while (true);
    }

    public void displayTeachers() {
        if (UniversityManager.teacherList.isEmpty()) {
            System.err.println("Danh sách giảng viên trống!");
            return;
        }

        UniversityManager.teacherList.stream()
                .sorted(Comparator.comparing(Teacher::getTeacherId).reversed())
                .forEach(System.out::println);
    }

    public void addTeacher(Scanner sc) {
        System.out.println("Nhập số lượng giảng viên cần thêm:");
        int size = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < size; i++) {
            System.out.println("Nhập thông tin giảng viên thứ " + (i + 1) + ":");
            Teacher teacher = new Teacher("", 0, "", "", "", Person.Gender.OTHER);
            teacher.inputData(sc);
            UniversityManager.teacherList.add(teacher);
            System.out.println("Thêm giảng viên thành công!");
        }
    }

    public void updateTeacher(Scanner sc) {
        System.out.println("Nhập vào id giảng viên cần cập nhật:");
        int teacherId = Integer.parseInt(sc.nextLine());

        int indexUpdate = findTeacherById(teacherId);

        if (indexUpdate == -1) {
            System.err.println("Không tìm thấy giảng viên!");
            return;
        }

        do {
            System.out.println("---------------CẬP NHẬT GIẢNG VIÊN----------------");
            System.out.println("1. Cập nhật tên giảng viên");
            System.out.println("2. Cập nhật tuổi");
            System.out.println("3. Cập nhật địa chỉ");
            System.out.println("4. Cập nhật số điện thoại");
            System.out.println("5. Cập nhật email");
            System.out.println("6. Cập nhật giới tính");
            System.out.println("7. Cập nhật chuyên môn giảng viên");
            System.out.println("8. Trở về menu quản lí giảng viên");
            System.out.printf("Lựa chọn của bạn: ");

            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    String teacherName = Validator.validateInputString(sc, "Nhập tên mới: ", new StringRule(0, 150));
                    UniversityManager.teacherList.get(indexUpdate).setName(teacherName);
                    System.out.println("Cập nhật tên thành công!");
                    break;
                case 2:
                    int newAge = PersonValidator.validateInputAge(sc, "Nhập tuổi mới: ", 18);
                    UniversityManager.teacherList.get(indexUpdate).setAge(newAge);
                    System.out.println("Cập nhật tuổi thành công!");
                    break;
                case 3:
                    String newAddress = Validator.validateInputString(sc, "Nhập địa chỉ mới: ", new StringRule());
                    UniversityManager.teacherList.get(indexUpdate).setAddress(newAddress);
                    System.out.println("Cập nhật địa chỉ thành công!");
                    break;
                case 4:
                    String newPhone = PersonValidator.validateInputPhone(sc, "Nhập số điện thoại: ", "^(0\\d{9}|\\+84\\d{9})$");
                    UniversityManager.teacherList.get(indexUpdate).setPhoneNumber(PersonValidator.validateExistPerson(sc, newPhone, "phoneNumber"));
                    System.out.println("Cập nhật số điện thoại thành công!");
                    break;
                case 5:
                    String newEmail = PersonValidator.validateInputEmail(sc, "Nhập email: ", "^[a-zA-Z0-9._]+@[a-zA-Z0-9]+\\.[a-zA-Z]{2,6}$");
                    UniversityManager.teacherList.get(indexUpdate).setEmail(PersonValidator.validateExistPerson(sc, newEmail, "email"));
                    System.out.println("Cập nhật email thành công!");
                    break;
                case 6:
                    Person.Gender newGender = Validator.validateEnumInput(sc, "Nhập giới tính mới: ", Person.Gender.class);
                    UniversityManager.teacherList.get(indexUpdate).setGender(newGender);
                    System.out.println("Cập nhật giới tính thành công!");
                    break;
                case 7:
                    String newSubject = Validator.validateInputString(sc, "Nhập chuyên môn: ", new StringRule());
                    UniversityManager.teacherList.get(indexUpdate).setSubject(newSubject);
                    System.out.println("Cập nhật chuyên môn thành công!");
                    break;
                case 8:
                    return;
                default:
                    System.err.println("Vui lòng chọn lại từ 1 - 8!");
            }
        } while (true);
    }

    public void deleteTeacher(Scanner sc){
        System.out.println("Nhập vào 1 id giảng viên cần xóa:");
        int id = Integer.parseInt(sc.nextLine());

        int indexDelete = findTeacherById(id);

        if (indexDelete == -1){
            System.err.println("Không tìm thấy sinh viên cần xóa!");
            return;
        }

        List<ClassRoom> filterTeacherById = UniversityManager.classRoomList.stream()
                .filter(classRoom -> classRoom.getTeacherId() == id)
                .toList();

        if(!filterTeacherById.isEmpty()){
            System.err.println("Giảng viên đã được xếp lớp. Không thể xóa!");
            return;
        }

        System.out.println("Bạn có xác nhận xóa giảng viên này không?");
        String confirm = sc.nextLine();

        if (confirm.equals("y")) {
            UniversityManager.teacherList.remove(indexDelete);
            System.out.println("Xóa giảng viên thành công");
        }else if(confirm.equals("n")) {
            System.out.println("Đã hủy xác nhận xóa!");
        }
    }

    public int findTeacherById(int id) {
        for (int i = 0; i < UniversityManager.teacherList.size(); i++) {
            if (UniversityManager.teacherList.get(i).getTeacherId() == id) {
                return i;
            }
        }
        return -1;
    }
}