package lk.ijse.hostel_management_system.dao.custom.impl;

import lk.ijse.hostel_management_system.entity.StudentEntitie;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class StudentDaoImpl {

    public StudentEntitie save(Session session, StudentEntitie entitie) throws SQLException {
        session.save(entitie);
        return entitie;
    }

    public List<StudentEntitie> getAll(Session session) {
        Query query = session.createQuery("From student");
        List<StudentEntitie> list = query.list();
        return list;
    }

    public StudentEntitie update(Session session, StudentEntitie entitie) {
        session.update(entitie);
        return entitie;
    }
   public boolean delete(Session session, String id){
       StudentEntitie studentEntitie = session.get(StudentEntitie.class, id);
       session.delete(studentEntitie);
        return studentEntitie!=null;

   }

}
