package lk.ijse.hostel_management_system.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name="room")
public class Room {
    @Id
    private String room_id;
    private String room_type;
    private double keyMoney;
    private int room_qty;

}
