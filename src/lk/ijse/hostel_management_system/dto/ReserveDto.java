package lk.ijse.hostel_management_system.dto;

import lk.ijse.hostel_management_system.entity.Room;
import lk.ijse.hostel_management_system.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ReserveDto {

    private String reserve_Id;

    private String start_Date;
    private LocalDate reserve_Date;


    private StudentDto student;//student_reserver

    private RoomDto room;
}
/*   @Id
    String res_id;
    LocalDate date;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id",referencedColumnName = "student_id")
    private Student student;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "room_type_id",referencedColumnName = "room_type_id")
    private Room room;
    String status;
    int qty;*/