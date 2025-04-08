package service.student;

import bussiness.dao.StudentDao.StudentDao;
import bussiness.dao.StudentDao.StudentDaoImp;
import bussiness.model.Student;

import java.util.List;

public class StudentServiceImp implements StudentService {
    private final StudentDao studentDao;

    public StudentServiceImp() {
        this.studentDao = new StudentDaoImp();
    }

    @Override
    public List<Student> findAll() {
        return studentDao.findAll() ;
    }

    @Override
    public boolean save(Student student) {
        return studentDao.save(student);
    }

    @Override
    public boolean update(Student student) {
        return studentDao.update(student);
    }

    @Override
    public boolean delete(Student student) {
        return studentDao.delete(student);
    }

    @Override
    public Student findById(int id) {
        return studentDao.findById(id);
    }
}
