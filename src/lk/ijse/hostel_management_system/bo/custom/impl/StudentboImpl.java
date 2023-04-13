package lk.ijse.hostel_management_system.bo.custom.impl;

import lk.ijse.hostel_management_system.dao.custom.impl.StudentDaoImpl;
import lk.ijse.hostel_management_system.dto.StudentDto;
import lk.ijse.hostel_management_system.entity.StudentEntitie;
import lk.ijse.hostel_management_system.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class StudentboImpl {
    Session session;
    Transaction transaction;


    StudentDaoImpl studentDao = new StudentDaoImpl();

    public boolean saveStudent(StudentDto dto) throws SQLException {
        openSession();
        StudentEntitie save = studentDao.save(session, new StudentEntitie(
                dto.getStudentId(), dto.getStudent_name(), dto.getStudent_address(), dto.getDob(), dto.getGender(), dto.getStudent_contact()
        ));
        closeSession();
        return save != null ? true : false;
    }

    public List<StudentDto> getAllStudent() {
        openSession();
        final List<StudentDto> list = studentDao.getAll(session).stream().map(c -> new StudentDto(
                c.getStudentId(),
                c.getStudent_name(),
                c.getStudent_address(),
                c.getDob(),
                c.getGender(),
                c.getStudent_contact())).collect(Collectors.toList());
        closeSession();
        for (StudentDto dto : list) {
            System.out.println(dto);
        }
        return list;
    }

    public boolean updateStudent(StudentDto dto) {
        openSession();
        StudentEntitie update = studentDao.update(session, new StudentEntitie(dto.getStudentId(), dto.getStudent_name(), dto.getStudent_address(), dto.getDob(), dto.getGender()
                , dto.getStudent_contact()));
        closeSession();
        return update != null ? true : false;

    }
    public boolean deleteStudent(String id){
        openSession();
        boolean delete = studentDao.delete(session, id);
        closeSession();
        return delete ;
    }


    public void openSession() {
        session = FactoryConfiguration.getInstance().getSession();
        transaction = session.beginTransaction();

    }

    public void closeSession() {
        transaction.commit();
        session.close();
    }

}