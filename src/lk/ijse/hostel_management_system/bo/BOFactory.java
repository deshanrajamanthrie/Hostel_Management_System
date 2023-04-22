package lk.ijse.hostel_management_system.bo;

import lk.ijse.hostel_management_system.bo.custom.impl.ReserveBoImpl;
import lk.ijse.hostel_management_system.bo.custom.impl.RoomBoImpl;
import lk.ijse.hostel_management_system.bo.custom.impl.StudentBoImpl;
import lk.ijse.hostel_management_system.dao.DAOType;

public class BOFactory {
    public static BOFactory boFactory;

    private BOFactory() {

    }

    public static BOFactory getInstances() {
        return boFactory == null ? boFactory = new BOFactory() : boFactory;
    }

    public SuperBO getBo(BOTypes boTypes) {
        switch (boTypes) {
            case STUDENT:
                return new StudentBoImpl();
            case ROOM:
                return new RoomBoImpl();
            case RESERVE:
                return new ReserveBoImpl();
        }
        return null;
    }

}
