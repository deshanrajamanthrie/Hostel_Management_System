package lk.ijse.hostel_management_system.entity;

import lk.ijse.hostel_management_system.dto.RoomDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity(name = "room")
public class Room {
    @Id
    private String room_id;
    private String room_type;
    private double keyMoney;
    private int room_qty;
    @ToString.Exclude
    @OneToMany(mappedBy = "room",cascade = CascadeType.ALL,targetEntity = Reserve.class)
    List<Reserve> reserveList;

    public Room(String room_id, String room_type, double keyMoney, int room_qty) {
        this.room_id = room_id;
        this.room_type = room_type;
        this.keyMoney = keyMoney;
        this.room_qty = room_qty;
    }

    public Room(String room_id) {
        this.room_id = room_id;
    }
}
