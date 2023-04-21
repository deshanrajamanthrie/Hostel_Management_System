package lk.ijse.hostel_management_system.dao.custom;

import lk.ijse.hostel_management_system.dao.CrudDao;
import lk.ijse.hostel_management_system.entity.Reserve;
import org.hibernate.Session;

public interface ReserveDao extends CrudDao<Reserve,String> {

     String genarateId(Session session);
}
