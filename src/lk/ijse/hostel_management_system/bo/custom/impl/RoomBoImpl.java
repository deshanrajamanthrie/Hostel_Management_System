package lk.ijse.hostel_management_system.bo.custom.impl;

import lk.ijse.hostel_management_system.dao.custom.impl.RoomDaoImpl;
import lk.ijse.hostel_management_system.dto.RoomDto;
import lk.ijse.hostel_management_system.entity.Room;
import lk.ijse.hostel_management_system.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

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


    public void openSession() {
        session = FactoryConfiguration.getInstance().getSession();
        transaction = session.beginTransaction();

    }

    public void closeSession() {
        transaction.commit();
        session.close();
    }
}
