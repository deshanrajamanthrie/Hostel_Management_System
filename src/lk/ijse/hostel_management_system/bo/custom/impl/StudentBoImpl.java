package lk.ijse.hostel_management_system.bo.custom.impl;

import lk.ijse.hostel_management_system.bo.custom.StudentBO;
import lk.ijse.hostel_management_system.dao.DAOFactory;
import lk.ijse.hostel_management_system.dao.DAOType;
import lk.ijse.hostel_management_system.dao.SuperDao;
import lk.ijse.hostel_management_system.dao.custom.StudentDao;
import lk.ijse.hostel_management_system.dao.custom.impl.StudentDaoImpl;
import lk.ijse.hostel_management_system.dto.StudentDto;
import lk.ijse.hostel_management_system.entity.Student;
import lk.ijse.hostel_management_system.util.FactoryConfiguration;
import lk.ijse.hostel_management_system.util.GenerateId;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class StudentBoImpl implements StudentBO {
    Session session;
    Transaction transaction;

    StudentDao studentDao = (StudentDao) DAOFactory.getInstance().getDao(DAOType.STUDENT);

    public boolean saveStudent(StudentDto dto) throws SQLException {
        openSession();
        StudentDao studentDao = (StudentDao) DAOFactory.getInstance().getDao(DAOType.STUDENT);
        Student save = studentDao.save(session, new Student(
                dto.getStudentId(), dto.getStudent_name(), dto.getStudent_address(), dto.getDob(), dto.getGender(), dto.getStudent_contact()
        ));
        closeSession();
        return save != null ? true : false;
    }

    public List<StudentDto> getAllStudent() throws SQLException {
        openSession();
        final List<StudentDto> list;
        list = studentDao.getAll(session).stream().map(c -> new StudentDto(
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

    public boolean updateStudent(StudentDto dto) throws SQLException {
        openSession();
        Student update = studentDao.update(session, new Student(dto.getStudentId(), dto.getStudent_name(), dto.getStudent_address(), dto.getDob(), dto.getGender()
                , dto.getStudent_contact()));
        closeSession();
        return update != null ? true : false;
    }

    public boolean deleteStudent(String id) throws SQLException {
        openSession();
        boolean delete = studentDao.delete(session, id);
        closeSession();
        return delete ;
    }
    public String genarateIdStudent(){
        openSession();
        String id = studentDao.genarateId(session);
        closeSession();
        return GenerateId.genarateNewId("R-",id);
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
