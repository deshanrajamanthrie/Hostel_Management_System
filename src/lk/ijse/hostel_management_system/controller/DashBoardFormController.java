package lk.ijse.hostel_management_system.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class DashBoardFormController {
    public AnchorPane context;

    public void homeOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/DashBoardForm.fxml"))));
    }

    public void StudentManageOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../view/StudentManageForm.fxml"));
        context.getChildren().clear();
        context.getChildren().add(parent);
    }

    public void roomOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../view/RoomManageForm.fxml"));
        context.getChildren().clear();
        context.getChildren().add(parent);

    }

    public void ReserveroomOnaction(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../view/ReserveRooms.fxml"));
        context.getChildren().clear();
        context.getChildren().add(parent);

    }

    public void reservationOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../view/RoomsReservationDetail.fxml"));
        context.getChildren().clear();
        context.getChildren().add(parent);


    }

    public void logoutOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(new Scene( FXMLLoader.load(getClass().getResource("../view/LoginForm.fxml"))));
        stage.show();
        stage.centerOnScreen();
    }
}
