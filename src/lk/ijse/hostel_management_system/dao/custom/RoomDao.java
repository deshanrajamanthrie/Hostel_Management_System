package lk.ijse.hostel_management_system.dao.custom;

import lk.ijse.hostel_management_system.dao.CrudDao;
import lk.ijse.hostel_management_system.entity.Room;
import org.hibernate.Session;

public interface RoomDao extends CrudDao<Room,String> {
 //   String genarateId(Session session);
 public Room search(Session session, String id);
}
