package lk.ijse.hostel_management_system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Roomdto {
    private String room_id;
    private String room_type;
    private double keymoney;
    private int room_qty;
}
