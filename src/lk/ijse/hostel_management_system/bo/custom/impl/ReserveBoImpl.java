package lk.ijse.hostel_management_system.bo.custom.impl;

import lk.ijse.hostel_management_system.dao.custom.ReserveDao;
import lk.ijse.hostel_management_system.dao.custom.StudentDao;
import lk.ijse.hostel_management_system.dao.custom.impl.ReserveDaoImpl;
import lk.ijse.hostel_management_system.dao.custom.impl.RoomDaoImpl;
import lk.ijse.hostel_management_system.dao.custom.impl.StudentDaoImpl;
import lk.ijse.hostel_management_system.dto.ReserveDto;
import lk.ijse.hostel_management_system.dto.RoomDto;
import lk.ijse.hostel_management_system.dto.StudentDto;
import lk.ijse.hostel_management_system.entity.Reserve;
import lk.ijse.hostel_management_system.entity.Room;
import lk.ijse.hostel_management_system.entity.Student;
import lk.ijse.hostel_management_system.util.FactoryConfiguration;
import lk.ijse.hostel_management_system.util.GenerateId;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReserveBoImpl {
    Session session;
    Transaction transaction;
    StudentDao studentDao = new StudentDaoImpl();
    RoomDaoImpl roomDao = new RoomDaoImpl();
    ReserveDao reserveDao = new ReserveDaoImpl();

    public List<String> getStudentId() {
        return studentDao.getStudentId();
    }

    public ReserveDto saveReserve(ReserveDto dto) throws SQLException {
        openSession();
        ReserveDaoImpl reserveDao = new ReserveDaoImpl();
        Room room1 = roomDao.search(session, dto.getRoom().getRoom_id());
        Student student = new Student();
        student.setStudentId(dto.getStudent().getStudentId());
        Reserve save = reserveDao.save(session, new Reserve(dto.getReserve_Id(), dto.getStart_Date(), Date.valueOf(dto.getReserve_Date()), student,
                room1));
        if (save != null) {
            room1.setRoom_qty(room1.getRoom_qty() - 1);
            closeSession();
        } else {
            transaction.rollback();
            session.close();
        }
        return save != null ? dto : null;
    }
    public String genarateIdReservation(){
        openSession();
        String id = reserveDao.genarateId(session);
        closeSession();
        return GenerateId.genarateNewId("RS-",id);
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
