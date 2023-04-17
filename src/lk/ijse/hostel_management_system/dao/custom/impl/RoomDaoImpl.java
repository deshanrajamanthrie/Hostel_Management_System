package lk.ijse.hostel_management_system.dao.custom.impl;

import lk.ijse.hostel_management_system.dao.custom.RoomDao;
import lk.ijse.hostel_management_system.entity.Room;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class RoomDaoImpl implements RoomDao {

    public Room save(Session session,Room entity){
         session.save(entity);
        return entity;
    }


    public List<Room>getAll(Session session){
        Query query = session.createQuery("FROM room ");
        List<Room> list = query.list();
        return list;


    }

    @Override
    public Room update(Session session, Room entitie) throws SQLException {
        session.update(entitie);
        return entitie;
    }

    @Override
    public boolean delete(Session session, String s) throws SQLException {
        Room room = session.get(Room.class, s);
        session.delete(room);
        return room!=null;

    }


}
