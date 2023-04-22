package lk.ijse.hostel_management_system.bo.custom.impl;

import lk.ijse.hostel_management_system.bo.SuperBO;
import lk.ijse.hostel_management_system.bo.custom.ReserveBO;
import lk.ijse.hostel_management_system.dao.DAOFactory;
import lk.ijse.hostel_management_system.dao.DAOType;
import lk.ijse.hostel_management_system.dao.SuperDao;
import lk.ijse.hostel_management_system.dao.custom.ReserveDao;
import lk.ijse.hostel_management_system.dao.custom.RoomDao;
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

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class ReserveBoImpl implements ReserveBO {
    Session session;
    Transaction transaction;

    StudentDao studentDao = (StudentDao) DAOFactory.getInstance().getDao(DAOType.STUDENT);
    RoomDao roomDao = (RoomDao) DAOFactory.getInstance().getDao(DAOType.ROOM);
    ReserveDao reserveDao = (ReserveDao) DAOFactory.getInstance().getDao(DAOType.RESERVE);

    public List<String> getStudentId() {

        return studentDao.getStudentId();
    }

    public ReserveDto saveReserve(ReserveDto dto) throws SQLException {
        openSession();
        ReserveDaoImpl reserveDao = new ReserveDaoImpl();


        Room room1 = roomDao.search(session, dto.getRoom().getRoomId());
        Student student = new Student();
        student.setStudentId(dto.getStudent().getStudentId());
        Reserve save = reserveDao.save(session, new Reserve(dto.getReserve_Id(), dto.getStart_Date(),
                Date.valueOf(String.valueOf((dto.getReserveDate()))), dto.getStatus(), student, room1));
        if (save != null) {
            room1.setRoom_qty(room1.getRoom_qty() - 1);
            closeSession();
        } else {
            transaction.rollback();
            session.close();
        }
        return save != null ? dto : null;
    }

    public String genarateIdReservation() {
        openSession();
        String id = reserveDao.genarateId(session);
        closeSession();
        return GenerateId.genarateNewId("RS-", id);
    }

    public List<ReserveDto> getAllReserve() throws SQLException {
        openSession();

        List<ReserveDto> list = reserveDao.getAll(session).stream().map(g -> new ReserveDto(
                g.getReserve_Id(),
                g.getStart_Date(),
                g.getReserve_Date(),
                g.getStatus(),
                new StudentDto(g.getStudent().getStudentId()),
                new RoomDto(g.getRoom().getRoom_id())
        )).collect(Collectors.toList());
        closeSession();
        for (ReserveDto reserve : list) {

        }
        return list;
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
