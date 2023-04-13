package lk.ijse.hostel_management_system.dao.custom.impl;

import lk.ijse.hostel_management_system.dao.custom.StudentDao;
import lk.ijse.hostel_management_system.entity.Student;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class StudentDaoImpl implements StudentDao {

    public Student save(Session session, Student entitie) throws SQLException {
        session.save(entitie);
        return entitie;
    }

    public List<Student> getAll(Session session) {
        Query query = session.createQuery("From student");
        List<Student> list = query.list();
        return list;
    }

    public Student update(Session session, Student entitie) {
        session.update(entitie);
        return entitie;
    }

    public boolean delete(Session session, String id) {
        Student studentEntitie = session.get(Student.class, id);
        session.delete(studentEntitie);
        return studentEntitie != null;

    }

}
