package lk.ijse.hostel_management_system.tm;

import javafx.scene.control.Button;
import lk.ijse.hostel_management_system.dto.ReserveDto;
import lk.ijse.hostel_management_system.dto.RoomDto;
import lk.ijse.hostel_management_system.dto.StudentDto;
import lk.ijse.hostel_management_system.entity.Room;
import lk.ijse.hostel_management_system.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class ReserveTM  {
    private String reserveId;
    private String startDate;
    private LocalDate reserveDate;
    private String status;
    private String studentId;
    private String roomId;
    private Button button;

}
