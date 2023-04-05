package lk.ijse.hostel_management_system.modle;
import lombok.*;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
 private String firstname;
 private String lastname;
 private String email;
 private String passeword;
}
