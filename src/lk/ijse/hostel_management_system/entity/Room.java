package lk.ijse.hostel_management_system.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "room")
public class Room {
    @Id
    private String room_id;
    private String room_type;
    private double keyMoney;
    private int room_qty;
    @OneToMany(mappedBy = "room",cascade = CascadeType.ALL,targetEntity = Reserve.class)
    List<Reserve> reserveList;

    public Room(String room_id, String room_type, double keyMoney, int room_qty) {
        this.room_id = room_id;
        this.room_type = room_type;
        this.keyMoney = keyMoney;
        this.room_qty = room_qty;
    }
}
