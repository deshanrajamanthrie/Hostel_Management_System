package lk.ijse.hostel_management_system.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Studentdto {
    private String studentId;
    private String student_name;
    private String student_address;
    private String dob;
    private String gender;
    private String student_contact;
}
