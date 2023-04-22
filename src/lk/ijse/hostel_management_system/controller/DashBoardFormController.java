package lk.ijse.hostel_management_system.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import lk.ijse.hostel_management_system.bo.BOFactory;
import lk.ijse.hostel_management_system.bo.BOTypes;
import lk.ijse.hostel_management_system.bo.custom.RoomBO;
import lk.ijse.hostel_management_system.bo.custom.impl.ReserveBoImpl;
import lk.ijse.hostel_management_system.dto.ReserveDto;
import lk.ijse.hostel_management_system.dto.RoomDto;
import lk.ijse.hostel_management_system.tm.ReserveTM;
import lk.ijse.hostel_management_system.tm.RoomTm;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class DashBoardFormController {
    public AnchorPane context;
    public Label lbltime;
    public Label lbldate;
    public TableView tblroomsAvailable;
    public TableColumn colroomType;
    public TableColumn colAvailability;
    public TableColumn coloperator;
    public TableView tblPaid;
    public TableColumn colStId;
    public TableColumn colstName;
    public TableColumn colPaid;
    public TableColumn colOperator2;

    RoomBO roomBo = (RoomBO) BOFactory.getInstances().getBo(BOTypes.ROOM);

    public void initialize() {
        try {
            getReservation();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        RunningTime();
        getAvailableroom();
        colroomType.setCellValueFactory(new PropertyValueFactory<>("room_type"));
        colAvailability.setCellValueFactory(new PropertyValueFactory<>("room_qty"));
        coloperator.setCellValueFactory(new PropertyValueFactory<>("btn"));
        //========================================================================


        colPaid.setCellValueFactory(new PropertyValueFactory<>("status"));
       colStId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        coloperator.setCellValueFactory(new PropertyValueFactory<>("btn"));
    }

    public void getAvailableroom() {

        try {
            List<RoomDto> allRoom = roomBo.getAllRoom();

            for (RoomDto roomDto : allRoom) {
                Button button = new Button("Delete");
                tblroomsAvailable.getItems().add(new RoomTm(roomDto.getRoomId(), roomDto.getRoomType()
                        , roomDto.getKeyMoney(), roomDto.getRoomQty(), button));

                button.setOnAction(event -> {
                    final int index = tblroomsAvailable.getSelectionModel().getSelectedIndex();
                    for (int i = 0; i < 100; i++) {
                        if (i == index) {
                            tblroomsAvailable.getItems().remove(i);
                        }
                    }
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void getReservation() throws SQLException {
        ReserveBoImpl reserveBo = new ReserveBoImpl();
       /* try {
            List<ReserveDto> allReserve = reserveBo.getAllReserve();
            for (ReserveDto r:allReserve) {
                Button button = new Button("Remove");
                tblPaid.getItems().add(new ReserveTM(r.getReserve_Id()
                        ,r.getStart_Date(),
                        r.getReserveDate().toLocalDate(),r.getStatus(),
                        r.getStudent().getStudentId(),r.getRoom().getRoomId(),button));
                button.setOnAction(event -> {
                    final int index1 = tblroomsAvailable.getSelectionModel().getSelectedIndex();
                    for (int i = 0; i < 100; i++) {
                        if (i == index1) {
                            tblPaid.getItems().remove(i);
                        }
                    }
                });

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }*/

        List<ReserveDto> allReserve = reserveBo.getAllReserve();
        for (ReserveDto r : allReserve) {
            System.out.println(r.getReserveDate());
            Button button = new Button("Remove Reserve");
            tblPaid.getItems().add(new ReserveTM(r.getReserve_Id(), r.getStart_Date(),
                    r.getReserveDate().toLocalDate(), r.getStatus(), r.getStudent().getStudentId(), r.getRoom().getRoomId(), button));
            button.setOnAction(event -> {
                        final int index = tblPaid.getSelectionModel().getSelectedIndex();
                        for (int i = 0; i < 100; i++) {
                            if (i == index) {
                                tblPaid.getItems().remove(i);
                            }
                        }
                    }
            );
        }
    }




    private void RunningTime() {
        lbldate.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

        final Thread thread = new Thread(() -> {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss a");
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                final String time = simpleDateFormat.format(new Date());
                Platform.runLater(() -> {
                    lbltime.setText(time);
                });
            }
        });
        thread.start();
    }

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
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/LoginForm.fxml"))));
        stage.show();
        stage.centerOnScreen();
    }


}
