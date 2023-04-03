package lk.ijse.hostel_management_system.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class LoginFormController {

    public AnchorPane context;
    public TextField txtusername;
    public PasswordField txtpasseword;



    public void loginOnAction(ActionEvent actionEvent) {

    }
    public void CreateAnAccountOnAction(ActionEvent actionEvent) throws IOException {
        setUi();
    }

    private void setUi() throws IOException {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/SingInForm.fxml"))));
        stage.setTitle("SignUp_Form");
        stage.show();
    }
}
