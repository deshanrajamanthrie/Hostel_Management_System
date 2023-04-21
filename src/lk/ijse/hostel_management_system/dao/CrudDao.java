package lk.ijse.hostel_management_system.dao;

import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;

public interface CrudDao<T, Id> extends SuperDao{

    T save(Session session, T entitie) throws SQLException;

    List<T> getAll(Session session) throws SQLException;

    T update(Session session, T entitie) throws SQLException;

    boolean delete(Session session, Id id) throws SQLException;
}
