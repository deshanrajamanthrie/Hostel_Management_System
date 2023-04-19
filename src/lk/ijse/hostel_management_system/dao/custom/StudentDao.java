package lk.ijse.hostel_management_system.dao.custom;

import lk.ijse.hostel_management_system.dao.CrudDao;
import lk.ijse.hostel_management_system.entity.Student;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public interface StudentDao extends CrudDao<Student,String> {
     List<String>getStudentId();

     public String genarateId(Session session) ;

}
