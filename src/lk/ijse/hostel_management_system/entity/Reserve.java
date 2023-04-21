package lk.ijse.hostel_management_system.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "reserve")
public class Reserve {
    @Id
    private String reserve_Id;
    @Column(name = "startDate")
    private String start_Date;
    private Date reserve_Date;//Date
    private String status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "studentId")
    private Student student;//student_reserver

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "roomId")
    private Room room;
}
/*private String reserveId;
    private String start_Date;
    private Date reserve_Date;
    private String status;
    private Student student;
    private Room room;
    private Button button;*/