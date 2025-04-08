package business;

import entity.ClassRoom;

import entity.Student;
import presentation.UniversityManager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class ClassRoomBusiness {
    public static void displayClassRoomMenu(Scanner sc) {
        ClassRoomBusiness classRoomBusiness = new ClassRoomBusiness();
        do {
            System.out.println("********************** CLASSROOM MENU **********************");
            System.out.println("1. Danh sách lớp học sắp xếp theo ngày tạo giảm dần");
            System.out.println("2. Thêm mới lớp học");
            System.out.println("3. Cập nhật thông tin lớp học (Chỉ cập nhật được thông tin nếu trạng thái khác \n" +
                    "CLOSE)");
            System.out.println("4. Xóa lớp học (Chỉ xóa được nếu lớp học chưa có sinh viên và giảng viên)");
            System.out.println("5. Phân công giảng viên cho lớp");
            System.out.println("6. Thêm sinh viên vào cho lớp");
            System.out.println("7. Tạo lịch học cho lớp");
            System.out.println("8. Hiển thị danh sách lịch học của lớp");
            System.out.println("9. Cập nhật trạng thái lớp (PENDING → PROGESS → CLOSE)");
            System.out.println("10. Trở về menu chính");
            System.out.printf("Lựa chọn của bạn: ");

            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    classRoomBusiness.displayClassRooms();
                    break;
                case 2:
                    classRoomBusiness.addClassRoom(sc);
                    break;
                case 3:
                    classRoomBusiness.updateClassRoom(sc);
                    break;
                case 4:
                    classRoomBusiness.deleteClassRoom(sc);
                    break;
                case 5:
                    classRoomBusiness.assignLecturer(sc);
                    break;
                case 6:
                    classRoomBusiness.addStudentToClass(sc);
                    break;
                case 7:
                    classRoomBusiness.createSchedule(sc);
                    break;
                case 8:
                    classRoomBusiness.displaySchedule(sc);
                    break;
                case 9:
                    classRoomBusiness.updateClassStatus(sc);
                    break;
                case 10:
                    return;
                default:
                    System.err.println("Vui lòng chọn lại từ 1 - 9!");
            }
        } while (true);
    }

    public void displayClassRooms() {
        if (UniversityManager.classRoomList.isEmpty()) {
            System.err.println("Danh sách lớp trống!");
            return;
        }

        UniversityManager.classRoomList.stream()
                .sorted(Comparator.comparing(ClassRoom::getCreatedAt).reversed())
                .forEach(System.out::println);
    }

    public void addClassRoom(Scanner sc) {
        System.out.println("Nhập số lượng lớp học cần thêm:");
        int size = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < size; i++) {
            System.out.println("Nhập thông tin lớp học thứ " + (i + 1) + ":");
            ClassRoom classRoom = new ClassRoom();
            classRoom.inputData(sc);
            UniversityManager.classRoomList.add(classRoom);
            System.out.println("Thêm lớp học thành công!");
        }
    }

    public void updateClassRoom(Scanner sc) {
        System.out.println("Nhập vào id lớp học cần cập nhật:");
        int classRoomId = Integer.parseInt(sc.nextLine());

        int indexUpdate = findClassRoomById(classRoomId);

        if (indexUpdate == -1) {
            System.err.println("Không tìm thấy lớp học!");
            return;
        }

        ClassRoom classRoom = UniversityManager.classRoomList.get(indexUpdate);

        if (classRoom.getStatus() == ClassRoom.Status.CLOSE) {
            System.err.println("Lớp học hiện không hoạt động. Vui lòng chọn lớp khác!");
            return;
        }

        do {
            System.out.println("--------------------CẬP NHẬT LỚP HỌC-----------------");
            System.out.println("1. Cập nhật tên lớp học");
            System.out.println("2. Câp nhật id khóa học");
            System.out.println("3. Cập nhật ngày tạo");
            System.out.println("4. Trở về menu quản lý lớp học");
            System.out.printf("Lựa chọn của bạn: ");

            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    String newClassRoomName = sc.nextLine();
                    classRoom.setClassroomName(newClassRoomName);
                    System.out.println("Cập nhật tên lớp học thành công!");
                    break;
                case 2:
                    String courseId = sc.nextLine();
                    classRoom.setCourseId(courseId);
                    System.out.println("Cập nhật id khóa học thành công!");
                    break;
                case 3:
                    LocalDate newCreatedAt = LocalDate.now();
                    classRoom.setCreatedAt(newCreatedAt);
                    System.out.println("Cập nhật ngày tạo thành công!");
                    break;
                case 4:
                    return;
                default:
                    System.err.println("Vui lòng chọn lại từ 1 - 4!");
            }
        } while (true);
    }

    public void deleteClassRoom(Scanner sc) {
        System.out.println("Nhập vào id lớp học cần xóa:");
        int classRoomId = Integer.parseInt(sc.nextLine());

        int indexDelete = findClassRoomById(classRoomId);

        if (indexDelete == -1) {
            System.err.println("Không tìm thấy lớp học!");
            return;
        }

        ClassRoom classRoom = UniversityManager.classRoomList.get(indexDelete);

        if (classRoom.getTeacherId() != 0 || (classRoom.getStudents() != null && !classRoom.getStudents().isEmpty())) {
            System.err.println("Lớp học đang có giảng viên hoặc sinh viên. Không thể xóa!");
            return;
        }

        System.out.println("Bạn có xác nhận xóa sinh viên này không?");
        String confirm = sc.nextLine();

        if (confirm.equals("y")) {
            UniversityManager.classRoomList.remove(indexDelete);
            System.out.println("Xóa lớp học thành công");
        }else if(confirm.equals("n")) {
            System.out.println("Đã hủy xác nhận xóa!");
        }
    }

    public void assignLecturer(Scanner sc) {
        int classRoomId = SelectAttribute.selectClassroomById(sc);

        System.out.println("Chọn giảng viên phân công cho lớp có id là " + classRoomId);
        int teacherId = SelectAttribute.selectTeacherById(sc);

        int indexAssign = findClassRoomById(classRoomId);

        UniversityManager.classRoomList.get(indexAssign).setTeacherId(teacherId);
        System.out.println("Phân công giảng viên thành công!");
    }

    public void addStudentToClass(Scanner sc) {
        System.out.println("Nhập id lớp học cần thêm sinh viên:");
        int classRoomId = Integer.parseInt(sc.nextLine());

        int indexAdd = findClassRoomById(classRoomId);

        if (indexAdd == -1) {
            System.err.println("Không tìm thấy lớp học!");
            return;
        }

        ClassRoom classRoom = UniversityManager.classRoomList.get(indexAdd);

        if (classRoom.getStudents() == null) {
            classRoom.setStudents(new ArrayList<>());
        }

        if (classRoom.getStudents().size() > ClassRoom.MAX_STUDENTS){
            System.err.println("Lớp đã đủ số lượng sinh viên!");
            return;
        }

        String studentId = SelectAttribute.selectStudentById(sc);

        boolean alreadyExist = classRoom.getStudents().stream()
                .anyMatch(student -> student.getStudentId().equals(studentId));

        if (alreadyExist) {
            System.err.println("Sinh viên đã có trong lớp!");
            return;
        }

        Student selectedStudent = UniversityManager.studentList.stream()
                .filter(student -> student.getStudentId().equals(studentId))
                .findFirst()
                .orElse(null);

        if (selectedStudent != null) {
            classRoom.getStudents().add(selectedStudent);
            System.out.println("Thêm sinh viên vào lớp thành công!");
        } else {
            System.out.println("Không tìm thấy sinh viên!");
        }
    }

    public void createSchedule(Scanner sc) {
        System.out.println("Nhâp số lịch học cần tạo:");
        int size = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < size; i++) {
            System.out.println("Nhập thông tin lịch học thứ " + (i + 1) + ":");
            Schedule schedule = new Schedule();
            schedule.inputData(sc);
            UniversityManager.scheduleList.add(schedule);
            System.out.println("Thêm lịch học thành công!");
        }
    }

    public void displaySchedule(Scanner sc) {
        System.out.println("Nhập id lớp học để hiển thị các lịch học:");
        int classRoomId = Integer.parseInt(sc.nextLine());

        List<Schedule> filterSchedule = UniversityManager.scheduleList.stream()
                .filter(schedule -> schedule.getClassroomId() == classRoomId)
                .toList();

        if (filterSchedule.isEmpty()){
            System.err.println("Lớp không có lịch học");
            return;
        }

        filterSchedule.forEach(System.out::println);
    }

    public void updateClassStatus(Scanner sc) {
        System.out.println("Nhập vào ID lớp học cần cập nhật trạng thái:");
        int classId = Integer.parseInt(sc.nextLine());

        int index = findClassRoomById(classId);
        if (index == -1) {
            System.err.println("Không tìm thấy lớp học!");
            return;
        }

        ClassRoom classRoom = UniversityManager.classRoomList.get(index);
        ClassRoom.Status currentStatus = classRoom.getStatus();

        System.out.println("Trạng thái hiện tại: " + currentStatus);

        ClassRoom.Status nextStatus = null;
        switch (currentStatus) {
            case PENDING:
                nextStatus = ClassRoom.Status.PROGRESS;
                break;
            case PROGRESS:
                nextStatus = ClassRoom.Status.CLOSE;
                break;
            case CLOSE:
                System.out.println("Lớp học đã đóng. Không thể cập nhật thêm.");
                return;
        }

        System.out.println("Bạn có chắc muốn cập nhật trạng thái lên '" + nextStatus + "'? (y/n)");
        String confirm = sc.nextLine().trim().toLowerCase();

        if (confirm.equals("y")) {
            classRoom.setStatus(nextStatus);
            System.out.println("Cập nhật trạng thái thành công!");
        } else {
            System.out.println("Hủy cập nhật trạng thái.");
        }
    }

    public int findClassRoomById(int id) {
        for (int i = 0; i < UniversityManager.classRoomList.size(); i++) {
            if (UniversityManager.classRoomList.get(i).getClassroomId() == id) {
                return i;
            }
        }
        return -1;
    }
}