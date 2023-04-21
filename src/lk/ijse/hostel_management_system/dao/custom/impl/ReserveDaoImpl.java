package lk.ijse.hostel_management_system.dao.custom.impl;

import lk.ijse.hostel_management_system.dao.custom.ReserveDao;
import lk.ijse.hostel_management_system.entity.Reserve;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class ReserveDaoImpl implements ReserveDao {
    public Reserve save(Session session, Reserve entiti) {
        session.save(entiti);
        return entiti;
    }

    @Override
    public List<Reserve> getAll(Session session) throws SQLException {
        Query query= session.createQuery("from  reserve");
        List <Reserve>list = query.list();
        return list;
    }

    @Override
    public Reserve update(Session session, Reserve entitie) throws SQLException {
        return null;
    }

    @Override
    public boolean delete(Session session, String s) throws SQLException {
        return false;
    }


    @Override
    public String genarateId(Session session) {
        Query query = session.createQuery("From reserve ORDER BY reserve_Id DESC");
        query.setCacheable(true);
        List<Reserve> resultList = query.getResultList();
        return resultList.size() == 0 ? null : resultList.get(0).getReserve_Id();

    }
}
