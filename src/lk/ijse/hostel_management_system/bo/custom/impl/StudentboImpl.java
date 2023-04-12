package lk.ijse.hostel_management_system.bo.custom.impl;

import lk.ijse.hostel_management_system.dao.custom.impl.StudentdaoImpl;
import lk.ijse.hostel_management_system.dto.Studentdto;
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
    StudentdaoImpl studentdao = new StudentdaoImpl();

    public boolean saveStudent(Studentdto dto) throws SQLException {
       OpenSession();
        StudentEntitie save = studentdao.save(session, new StudentEntitie(
                dto.getStudentId(), dto.getStudent_name(), dto.getStudent_address(), dto.getDob(), dto.getGender(), dto.getStudent_contact()
        ));
        closeSession();
        return save != null ? true : false;
    }
    public List<Studentdto> getAllStudent(){
        OpenSession();
        final List<Studentdto>list = studentdao.getAll(session).stream().map(c -> new Studentdto(
                c.getStudentId(),
                c.getStudent_name(),
                c.getStudent_address(),
                c.getDob(),
                c.getGender(),
                c.getStudent_contact())).collect(Collectors.toList());
        closeSession();
        for (Studentdto dto: list) {
            System.out.println( dto);
        }
        return  list;
    }
    public boolean updateStudent(Studentdto dto){
        OpenSession();
        StudentEntitie update = studentdao.update(session, new StudentEntitie(dto.getStudent_name(), dto.getStudent_address(), dto.getDob(), dto.getGender()
                , dto.getStudent_contact(), dto.getStudentId()));
        closeSession();
        return update !=null?true:false;
    }






    public void OpenSession() {
        session = FactoryConfiguration.getInstance().getSession();
        transaction = session.beginTransaction();

    }

    public void closeSession() {
        transaction.commit();
        session.close();
    }

}
