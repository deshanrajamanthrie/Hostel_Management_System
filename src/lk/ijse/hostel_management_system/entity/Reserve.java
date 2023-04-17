package lk.ijse.hostel_management_system.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "reserve")
public class Reserve {
    @Id
    private String reserve_Id;
    @Column(name="start_Date")
    private  String start_Date;
    private LocalDate reserve_Date;

    @ManyToOne
    private Student student_Id;
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