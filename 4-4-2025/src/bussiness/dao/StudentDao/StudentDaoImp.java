package bussiness.dao.StudentDao;

import bussiness.config.ConnectionDB;
import bussiness.model.Student;
import kotlin.reflect.KCallable;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImp implements StudentDao{

    @Override
    public List<Student> findAll() {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Student> studentList= new ArrayList<>() ;
        try {
            conn = ConnectionDB.openConnection();
            callSt=conn.prepareCall("call select_all_user()");
            ResultSet rs = callSt.executeQuery();
//            studentList = new ArrayList<>();
            while (rs.next()){
             Student newStudent = new Student();
             newStudent.setId(rs.getInt("user_id"));
             newStudent.setName(rs.getString("user_name"));
             newStudent.setAge(rs.getInt("user_age"));
             studentList.add(newStudent);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(conn);

        }
        return studentList;
    }

    @Override
    public boolean save(Student student) {

        try (
                Connection conn = ConnectionDB.openConnection();
                CallableStatement callSt = conn.prepareCall("call insert_new_user(?,?)");
                ) {


            callSt.setString(1,student.getName());
            callSt.setInt(2,student.getAge());
            callSt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }catch (Exception e) {
            e.fillInStackTrace();
        }

        return false;
    }

    @Override
    public Student findById(int id) {
        Connection conn = null;
        CallableStatement callSt= null;
        Student student = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt=conn.prepareCall("call find_by_id(?)");
            callSt.setInt(1,id);
            ResultSet rs =callSt.executeQuery();
            if(rs.next()){
                student=new Student();
                student.setId(rs.getInt("user_id"));
                student.setName(rs.getString("user_name"));
                student.setAge(rs.getInt("user_age"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  student;
    }
    @Override
    public boolean update(Student student) {
        Connection conn = null;
        CallableStatement callSt=null;
        try {
            conn=ConnectionDB.openConnection();
            callSt=conn.prepareCall("call update_user(?,?,?)");
            callSt.setInt(1,student.getId());
            callSt.setString(2,student.getName());
            callSt.setInt(3,student.getAge());
            callSt.execute();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(Student student) {
        Connection conn = null;
        CallableStatement callSt=null;
        try {
            conn=ConnectionDB.openConnection();
            callSt=conn.prepareCall("call delete_user(?)");
            callSt.setInt(1,student.getId());
            callSt.execute();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;

    }



}
