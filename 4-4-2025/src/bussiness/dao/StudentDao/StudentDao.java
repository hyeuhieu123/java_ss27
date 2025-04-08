package bussiness.dao.StudentDao;

import bussiness.dao.AppDao;
import bussiness.model.Student;

public interface StudentDao extends AppDao<Student> {
        Student findById(int id);
}
