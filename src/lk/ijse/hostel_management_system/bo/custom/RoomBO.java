package lk.ijse.hostel_management_system.bo.custom;

import lk.ijse.hostel_management_system.bo.SuperBO;
import lk.ijse.hostel_management_system.dto.RoomDto;
import lk.ijse.hostel_management_system.entity.Room;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public interface RoomBO extends SuperBO {
    boolean saveRoom(RoomDto dto);

    List<RoomDto> getAllRoom() throws SQLException;

    Room searchRoom(String id);

    boolean updateRoom(RoomDto dto) throws SQLException;

    boolean deleteRoom(String id) throws SQLException;
}
