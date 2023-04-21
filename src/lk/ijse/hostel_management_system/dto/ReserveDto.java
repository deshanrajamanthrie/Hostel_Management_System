package lk.ijse.hostel_management_system.dto;

import lk.ijse.hostel_management_system.entity.Reserve;
import lk.ijse.hostel_management_system.entity.Room;
import lk.ijse.hostel_management_system.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReserveDto {
    private String reserve_Id;
    private String start_Date;
    private Date reserveDate;
    private String status;
    private StudentDto student;
    private RoomDto room;
}
