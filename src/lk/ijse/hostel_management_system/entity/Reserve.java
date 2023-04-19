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

    @ManyToOne
    @JoinColumn(name="studentId")
    private Student student;//student_reserver

    @ManyToOne
    @JoinColumn(name = "roomId")
    private Room room;
}