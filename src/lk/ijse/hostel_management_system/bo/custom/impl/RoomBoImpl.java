package lk.ijse.hostel_management_system.bo.custom.impl;

import lk.ijse.hostel_management_system.dao.custom.impl.RoomDaoImpl;
import lk.ijse.hostel_management_system.dto.RoomDto;
import lk.ijse.hostel_management_system.dto.StudentDto;
import lk.ijse.hostel_management_system.entity.Room;
import lk.ijse.hostel_management_system.entity.Student;
import lk.ijse.hostel_management_system.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RoomBoImpl {
    Session session;
    Transaction transaction;
    RoomDaoImpl roomdao = new RoomDaoImpl();


    public boolean saveRoom(RoomDto dto) {
        openSession();
        Room save = roomdao.save(session, new Room(dto.getRoom_id(), dto.getRoom_type(), dto.getKeymoney(), dto.getRoom_qty()));
        closeSession();
        if (save != null) {
            return true;
        } else {
            return false;
        }
    }

    public List<RoomDto> getAllRoom() {
        openSession();
        List<RoomDto> list = roomdao.getAll(session).stream().map(r -> new RoomDto(
                r.getRoom_id(),
                r.getRoom_type(),
                r.getKeyMoney(),
                r.getRoom_qty())).collect(Collectors.toList());
        closeSession();
        for (RoomDto dto : list) {
            System.out.println(dto);

        }
        return list;
    }
    public Room searchRoom(String id){
        openSession();
        Room search = roomdao.search(session, id);
        closeSession();
        return search;
    }

    public boolean updateRoom(RoomDto dto) throws SQLException {
        openSession();
        Room update = roomdao.update(session, new Room( dto.getRoom_id(),dto.getRoom_type(), dto.getKeymoney(),dto.getRoom_qty()));
        System.out.println(update);
        closeSession();
        return update != null ? true : false;
    }

    public boolean deleteRoom(String id) throws SQLException {
        openSession();
        boolean delete = roomdao.delete(session, id);
        closeSession();
        return delete;
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
