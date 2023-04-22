package lk.ijse.hostel_management_system.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.hostel_management_system.bo.BOFactory;
import lk.ijse.hostel_management_system.bo.BOTypes;
import lk.ijse.hostel_management_system.bo.SuperBO;
import lk.ijse.hostel_management_system.bo.custom.StudentBO;
import lk.ijse.hostel_management_system.bo.custom.impl.RoomBoImpl;
import lk.ijse.hostel_management_system.bo.custom.impl.StudentBoImpl;
import lk.ijse.hostel_management_system.dao.DAOType;
import lk.ijse.hostel_management_system.dto.StudentDto;
import lk.ijse.hostel_management_system.tm.StudentTm;

import java.sql.SQLException;
import java.util.List;
import java.util.Locale;

public class StudentManageFormController {

    public JFXTextField txtStudentId;
    public JFXTextField txtStudentName;
    public JFXTextField txtstudentadress;
    public JFXTextField txtDob;
    public JFXTextField genderspId;
    public JFXTextField txtContactNo;
    public TableView<StudentDto> tblStudent;
    public TableColumn colid;
    public TableColumn colname;
    public TableColumn coladdress;
    public TableColumn coldateofbirth;
    public TableColumn colgender;
    public TableColumn colcontact;
    public TableColumn coloperator;

    StudentBO studentbo = (StudentBO) BOFactory.getInstances().getBo(BOTypes.STUDENT);
    // StudentBoImpl studentbo = new StudentBoImpl();

    public void initialize() {
        getallStudents();

        colid.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colname.setCellValueFactory(new PropertyValueFactory<>("student_name"));
        coladdress.setCellValueFactory(new PropertyValueFactory<>("student_address"));
        coldateofbirth.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colgender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        colcontact.setCellValueFactory(new PropertyValueFactory<>("student_contact"));
        coloperator.setCellValueFactory(new PropertyValueFactory<>("btn"));

        tblStudent.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                txtStudentId.setText(newValue.getStudentId());
                txtStudentName.setText(newValue.getStudent_name());
                txtstudentadress.setText(newValue.getStudent_address());
                txtDob.setText(newValue.getDob());
                genderspId.setText(newValue.getGender());
                txtContactNo.setText(newValue.getStudent_contact());
            }
        });

    }

    private void getallStudents() {
        if (tblStudent.getItems().size() >= 0) {
            tblStudent.getItems().clear();
        }
        List<StudentDto> allStudent = null;
        try {
            allStudent = studentbo.getAllStudent();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (StudentDto dto : allStudent) {
            Button btn = new Button("Remove Student");
            tblStudent.getItems().add(new StudentTm(dto.getStudentId(), dto.getStudent_name(), dto.getStudent_address(),
                    dto.getDob(), dto.getGender(), dto.getStudent_contact(), btn));

            btn.setOnAction(event -> {
                        final int index = tblStudent.getSelectionModel().getSelectedIndex();
                        for (int i = 0; i < 1000; i++) {
                            if (index == i) {
                                tblStudent.getItems().remove(i);
                            }
                        }
                    }
            );
        }
    }

    public void SaveOnAction(ActionEvent actionEvent) {
        String id = txtStudentId.getText();
        String name = txtStudentName.getText();
        String address = txtstudentadress.getText();
        String dob = txtDob.getText();
        String gender = genderspId.getText();
        String contact = txtContactNo.getText();

        StudentDto studentdto = new StudentDto(id, name, address, dob, gender, contact);
        System.out.println(studentdto);

        try {
            boolean isAdded = studentbo.saveStudent(studentdto);
            if (isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "Saved Succesed !").show();
                initialize();
            } else {
                new Alert(Alert.AlertType.ERROR, "Try Again").show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void UpdateOnAction(ActionEvent actionEvent) {
        boolean b = false;
        try {
            b = studentbo.updateStudent(new StudentDto(txtStudentId.getText(), txtStudentName.getText(), txtstudentadress.getText()
                    , txtDob.getText(), genderspId.getText(), txtContactNo.getText()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (b == true) {
            new Alert(Alert.AlertType.INFORMATION, "Updated!").show();
            initialize();
            txtStudentId.clear();
            txtStudentName.clear();
            txtstudentadress.clear();
            txtDob.clear();
            txtContactNo.clear();
        } else {
            new Alert(Alert.AlertType.NONE, "Not Updated Try Again!").show();
        }
    }

    public void deleteOnAction(ActionEvent actionEvent) {
        boolean isdeleted = false;
        try {
            isdeleted = studentbo.deleteStudent(txtStudentId.getText());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (isdeleted == true) {
            new Alert(Alert.AlertType.CONFIRMATION, "Delete Successed").show();
            initialize();
            txtStudentId.clear();
            txtStudentName.clear();
            txtstudentadress.clear();
            txtDob.clear();
            txtContactNo.clear();
        } else {
            new Alert(Alert.AlertType.ERROR, "Not Deleted ,Try again!").show();
        }
    }


}
