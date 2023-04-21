package lk.ijse.hostel_management_system.dao;

import lk.ijse.hostel_management_system.dao.custom.impl.ReserveDaoImpl;
import lk.ijse.hostel_management_system.dao.custom.impl.RoomDaoImpl;
import lk.ijse.hostel_management_system.dao.custom.impl.StudentDaoImpl;

public class DAOFactory {
    public static DAOFactory daoFactory;

    private DAOFactory() {

    }

    public static DAOFactory getInstance() {
        return daoFactory == null ? daoFactory = new DAOFactory() : daoFactory;
    }

    public SuperDao getDao(DAOType daoType) {
        switch (daoType) {
            case STUDENT:
                return new StudentDaoImpl();
            case ROOM:
                return new RoomDaoImpl();
            case RESERVE:
                return new ReserveDaoImpl();
            default:
                return null;
        }
    }


}
