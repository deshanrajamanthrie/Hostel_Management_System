package lk.ijse.hostel_management_system.tm;

import javafx.scene.control.Button;
import lk.ijse.hostel_management_system.dto.RoomDto;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class RoomTm extends RoomDto {
    private String room_id;
    private String room_type;
    private double keymoney;
    private int room_qty;
    private Button btn;
}
