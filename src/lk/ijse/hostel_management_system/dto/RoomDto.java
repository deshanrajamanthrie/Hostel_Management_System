package lk.ijse.hostel_management_system.dto;

import lk.ijse.hostel_management_system.entity.Room;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class RoomDto  {
    private String roomId;
    private String roomType;
    private double keyMoney;
    private int roomQty;

    public RoomDto(String roomId) {
        this.roomId = roomId;
    }


}
