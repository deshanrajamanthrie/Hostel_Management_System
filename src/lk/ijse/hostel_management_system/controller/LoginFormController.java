package lk.ijse.hostel_management_system.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import lk.ijse.hostel_management_system.db.Tmdatabase;
import lk.ijse.hostel_management_system.modle.User;
import lk.ijse.hostel_management_system.util.security.PassewordManage;

import java.io.IOException;
import java.util.Optional;

public class LoginFormController {

    public AnchorPane context;
    public TextField txtemail;
    public PasswordField txtPasseword;


    public void loginOnAction(ActionEvent actionEvent) throws IOException {
        String emial = txtemail.getText().toLowerCase();
        String passeword = txtPasseword.getText().trim();
        Optional<User> getSelected = Tmdatabase.usertable.stream().filter(e -> e.getEmail().equals(emial)).findFirst();  // Convert to Stream
        if (getSelected.isPresent()) {
            if (new PassewordManage().chekpasseword(passeword, getSelected.get().getPasseword())) {
                System.out.println(getSelected.toString());
                dashBoardui();
            } else if (getSelected.isPresent() != new PassewordManage().chekpasseword(passeword, getSelected.get().getPasseword())) {
                new Alert(Alert.AlertType.ERROR, "Wrong Passeword").show();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "User Not Found").show();
        }
    }

    private void dashBoardui() throws IOException {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/DashBoardForm.fxml"))));
        stage.setTitle("DashBoard");
        stage.show();
        stage.centerOnScreen();

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
