package lk.ijse.hostel_management_system.bo.custom;

import lk.ijse.hostel_management_system.bo.SuperBO;
import lk.ijse.hostel_management_system.dao.custom.impl.ReserveDaoImpl;
import lk.ijse.hostel_management_system.dto.ReserveDto;
import lk.ijse.hostel_management_system.entity.Reserve;
import lk.ijse.hostel_management_system.entity.Room;
import lk.ijse.hostel_management_system.entity.Student;
import lk.ijse.hostel_management_system.util.GenerateId;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public interface ReserveBO extends SuperBO {
    public ReserveDto saveReserve(ReserveDto dto) throws SQLException ;

    public String genarateIdReservation() ;

    public List<ReserveDto> getAllReserve() throws SQLException ;

}
