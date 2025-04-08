package presentation;

import bussiness.model.Student;
import service.student.StudentService;
import service.student.StudentServiceImp;

import java.util.List;
import java.util.Scanner;

public class StudentUI {
    private final StudentService studentService;

    public StudentUI() {
        this.studentService = new StudentServiceImp() {
        };
    }


    public static void displayMenu(Scanner sc){
        StudentUI studentUI = new StudentUI();
        do{
            System.out.println("-----------student menu------------------");
            System.out.println("1. danh sach");
            System.out.println("2. them ");
            System.out.println("3. cap nhat ");
            System.out.println("4. xoa ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice){
                case 1:
                    studentUI.printList();
                    break;
                case 2:
                    studentUI.addUser(sc);
                    break;
                case 3:
                    studentUI.updateUser(sc);
                    break;
                case 4:
                    studentUI.deleteUser(sc);
                    break;
                default:
                    System.out.println("chon lai");
            }
        }while (true);
    }

    public void printList(){
        List<Student> list = studentService.findAll();
        for (Student std : list){
            System.out.println(std);
        }
    }
    public void addUser(Scanner sc)  {
        Student newStudent = new Student();
        newStudent.inputData(sc);
       if( studentService.save(newStudent)){
           System.out.println("them thanh cong ");
       }else System.out.println("them that bai");
    }


    public void updateUser(Scanner sc){
        System.out.println("nhap id ");
        Student student = studentService.findById(Integer.parseInt(sc.nextLine()));
        if(student != null){
            student.inputData(sc);
           if ( studentService.update(student) ){
               System.out.println("cap nhat thanh cong ");
           }else System.out.println("cap nhat that bai");
        }else System.out.println("ko tim thay");
    }
    public void deleteUser(Scanner sc){
        System.out.println("nhap id ");
        Student student = studentService.findById(Integer.parseInt(sc.nextLine()));
        if(student != null){
            if ( studentService.delete(student) ){
                System.out.println("xoa thanh cong ");
            }else System.out.println("xoa that bai");
        }else System.out.println("ko tim thay");
    }
}
