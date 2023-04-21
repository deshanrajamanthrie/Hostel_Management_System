package lk.ijse.hostel_management_system.dto;

import lk.ijse.hostel_management_system.entity.Student;
import lombok.*;

@NoArgsConstructor

@Getter
@Setter
@ToString
public class StudentDto {
    private String studentId;
    private String student_name;
    private String student_address;
    private String dob;
    private String gender;
    private String student_contact;

    public StudentDto(String studentId) {
        this.studentId = studentId;
    }

    public StudentDto(String studentId, String student_name, String student_address, String dob, String gender, String student_contact) {
        this.studentId = studentId;
        this.student_name = student_name;
        this.student_address = student_address;
        this.dob = dob;
        this.gender = gender;
        this.student_contact = student_contact;


    }




}
