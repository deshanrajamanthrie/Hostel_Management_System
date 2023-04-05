package lk.ijse.hostel_management_system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Studentdto {
    private String studentId;
    private String student_name;
    private String student_address;
    private String dob;
    private String gender;
    private String student_contact;
}
