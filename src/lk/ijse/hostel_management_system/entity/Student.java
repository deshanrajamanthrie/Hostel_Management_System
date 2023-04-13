package lk.ijse.hostel_management_system.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

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

  /*  public StudentEntities(String studentId, String student_name, String studentAddress, String dob, String gender, String student_contact) {
        this.studentId = studentId;
        this.student_name = student_name;
        this.studentAddress = studentAddress;
        this.dob = dob;
        this.gender = gender;
        this.student_contact = student_contact;*/

}
