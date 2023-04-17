package lk.ijse.hostel_management_system.tm;

import javafx.scene.control.Button;
import lk.ijse.hostel_management_system.dto.StudentDto;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentTm extends StudentDto {
    private String studentId;
    private String student_name;
    private String student_address;
    private String dob;
    private String gender;
    private String student_contact;
    private Button btn;


}
