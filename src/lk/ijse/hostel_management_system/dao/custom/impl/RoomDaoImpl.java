package lk.ijse.hostel_management_system.dao.custom.impl;

import lk.ijse.hostel_management_system.entity.Room;
import org.hibernate.Session;

public class RoomDaoImpl {

    public Room save(Session session,Room entity){
         session.save(entity);
        return entity;
    }

}
