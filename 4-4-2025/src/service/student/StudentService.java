package service.student;

import bussiness.model.Student;
import service.AppService;

public interface StudentService extends AppService<Student> {
    Student findById(int id);

}
