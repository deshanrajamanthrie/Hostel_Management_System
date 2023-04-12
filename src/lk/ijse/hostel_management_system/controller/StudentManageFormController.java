package lk.ijse.hostel_management_system.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.hostel_management_system.bo.custom.impl.StudentboImpl;
import lk.ijse.hostel_management_system.dto.Studentdto;
import lk.ijse.hostel_management_system.tm.Student_Tm;

import java.sql.SQLException;
import java.util.List;

public class StudentManageFormController {

    public JFXTextField txtStudentId;
    public JFXTextField txtStudentName;
    public JFXTextField txtstudentadress;
    public JFXTextField txtDob;
    public JFXTextField genderspId;
    public JFXTextField txtContactNo;
    public TableView<Studentdto> tblStudent;
    public TableColumn colid;
    public TableColumn colname;
    public TableColumn coladdress;
    public TableColumn coldateofbirth;
    public TableColumn colgender;
    public TableColumn colcontact;
    public TableColumn coloperator;

    StudentboImpl studentbo = new StudentboImpl();

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
        StudentboImpl studentbo = new StudentboImpl();
        List<Studentdto> allStudent = studentbo.getAllStudent();

        for (Studentdto dto : allStudent) {
            Button btn = new Button("Remove Student");
            tblStudent.getItems().add(new Student_Tm(dto.getStudentId(), dto.getStudent_name(), dto.getStudent_address(),
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

        Studentdto studentdto = new Studentdto(id, name, address, dob, gender, contact);
        System.out.println(studentdto);

        try {
            boolean isAdded = studentbo.saveStudent(studentdto);

            if (isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "Saved Succesed !").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Try Again").show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void UpdateOnAction(ActionEvent actionEvent) {
       /* boolean b = studentbo.updateStudent(new Studentdto(txtStudentId.getText(), txtStudentName.getText(), txtstudentadress.getText()
                , txtDob.getText(), genderspId.getText(), txtContactNo.getText()));
            if(b==true){
                new Alert(Alert.AlertType.INFORMATION,"Updated!").show();
            }else {
                new Alert(Alert.AlertType.NONE,"Not Updated Try Again!").show();
            }*/
        String id = txtStudentId.getText();
        String name = txtStudentName.getText();
        String address = txtstudentadress.getText();
        String dob = txtDob.getText();
        String gender = genderspId.getText();
        String contact = txtContactNo.getText();
        boolean b = studentbo.updateStudent(new Studentdto(id, name, address, dob, gender, contact));
        Studentdto selectedItem = tblStudent.getSelectionModel().getSelectedItem();
        selectedItem.setStudentId(id);
        selectedItem.setStudent_name(name);
        selectedItem.setStudent_address(address);
        selectedItem.setDob(dob);
        selectedItem.setGender(gender);
        selectedItem.setStudent_contact(contact);
        new Alert(Alert.AlertType.CONFIRMATION,"Updated!").show();
        tblStudent.refresh();
    }

    public void deleteOnAction(ActionEvent actionEvent) {

    }

    public void searchOnAction(ActionEvent actionEvent) {

    }
}
