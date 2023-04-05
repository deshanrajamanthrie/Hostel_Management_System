package lk.ijse.hostel_management_system.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import lk.ijse.hostel_management_system.db.Tmdatabase;
import lk.ijse.hostel_management_system.modle.User;
import lk.ijse.hostel_management_system.util.security.PassewordManage;

import java.io.IOException;

public class SingInFormController {
    public TextField txtfirstname;
    public TextField txtlastname;
    public TextField txtemail;
    public TextField txtpasseword;
    public AnchorPane singupContent;

    public void SignUpOnAction(ActionEvent actionEvent) throws IOException {
        String firstname=txtfirstname.getText().toLowerCase().trim();
        String lastname=txtfirstname.getText().toLowerCase().trim();
        String email= txtemail.getText();
        String passewrod=new PassewordManage().encode(txtpasseword.getText().trim());
        Tmdatabase.usertable.add(new User(firstname,lastname,email,passewrod));
        new Alert(Alert.AlertType.CONFIRMATION,"Welcome");
        setui();

    }

    public void AlreadyOnAction(ActionEvent actionEvent) throws IOException {
        setui();
    }

    private void setui() throws IOException {
        Stage stage = (Stage) singupContent.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/LoginForm.fxml"))));
        stage.show();

    }
}
