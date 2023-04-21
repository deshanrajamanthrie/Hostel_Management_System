package lk.ijse.hostel_management_system.bo.custom;

import lk.ijse.hostel_management_system.bo.SuperBO;
import lk.ijse.hostel_management_system.dto.StudentDto;
import lk.ijse.hostel_management_system.entity.Student;
import lk.ijse.hostel_management_system.util.GenerateId;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public interface StudentBO extends SuperBO {
    boolean saveStudent(StudentDto dto) throws SQLException;

    List<StudentDto> getAllStudent();

    boolean updateStudent(StudentDto dto);

    boolean deleteStudent(String id);

    String genarateIdStudent();
}
