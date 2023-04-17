package lk.ijse.hostel_management_system.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "student")
public class Student {
    @Id
    private String studentId;
    private String student_name;
    private String student_address;
    private String dob;
    private String gender;
    private String student_contact;

   /* @OneToMany(mappedBy = "student",fetch = FetchType.LAZY)
    private List<Reserve> reserveList;


    public Student(String studentId, String student_name, String student_address, String dob, String gender, String student_contact) {
        this.studentId = studentId;
        this.student_name = student_name;
        this.student_address = student_address;
        this.dob = dob;
        this.gender = gender;
        this.student_contact = student_contact;
    }*/


}
