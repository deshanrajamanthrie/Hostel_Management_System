package lk.ijse.hostel_management_system.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.hostel_management_system.bo.custom.impl.RoomBoImpl;
import lk.ijse.hostel_management_system.dto.RoomDto;
import lk.ijse.hostel_management_system.tm.RoomTm;

import java.sql.SQLException;
import java.util.List;

public class RoomManageFormController {
    public JFXTextField txtroomId;
    public JFXTextField txtkeymoney;
    public JFXTextField txtroomQty;
    public JFXComboBox<String> cmbRoomType;
    public JFXComboBox<String> cmbRoomId;
    public TableView<RoomDto> tblroom;
    public TableColumn colRoomId;
    public TableColumn colRoomType;
    public TableColumn colKeyMoney;
    public TableColumn colRoomQty;
    public TableColumn colOperator;

    RoomBoImpl roomBo = new RoomBoImpl();

    public void initialize() {
        cmbTypeListner();
        cmbIdListner();
        getAllRooms();
        showTableData();



    }

    private void showTableData() {
        colRoomId.setCellValueFactory(new PropertyValueFactory<>("room_id"));
        colRoomType.setCellValueFactory(new PropertyValueFactory<>("room_type"));
        colKeyMoney.setCellValueFactory(new PropertyValueFactory<>("keymoney"));
        colRoomQty.setCellValueFactory(new PropertyValueFactory<>("room_qty"));
        colOperator.setCellValueFactory(new PropertyValueFactory<>("btn"));

        tblroom.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
               cmbRoomId.setValue(newValue.getRoom_id());
                cmbRoomType.setValue(newValue.getRoom_type());
                txtkeymoney.setText(String.valueOf(newValue.getKeymoney()));
                txtroomQty.setText(String.valueOf(newValue.getRoom_qty()));
            }
        });

    }

    private void getAllRooms() {
        List<RoomDto> allStudent = roomBo.getAllStudent();

        for (RoomDto r : allStudent) {
            Button btn = new Button("Remove Room");
            tblroom.getItems().add(new RoomTm(r.getRoom_id(), r.getRoom_type(), r.getKeymoney(), r.getRoom_qty(), btn));

            btn.setOnAction(event -> {
                final int index = tblroom.getSelectionModel().getSelectedIndex();
                for (int i = 0; i < 100; i++) {
                    if (i == index) {
                        tblroom.getItems().remove(i);
                    }
                }
            });

        }

    }
    private void cmbIdListner() {
        ObservableList<String> cmbIdlist = FXCollections.observableArrayList();
        cmbIdlist.add("RM-1324");
        cmbIdlist.add("RM-5467");
        cmbIdlist.add("RM-7896 ");
        cmbIdlist.add("RM-0093");
        cmbRoomId.setItems(cmbIdlist);
    }

    public void cmbTypeListner() {
        ObservableList<String> cmbList = FXCollections.observableArrayList();
        cmbList.add("Non-AC");
        cmbList.add("Non-AC / Food");
        cmbList.add("AC ");
        cmbList.add("AC / Food");
        cmbRoomType.setItems(cmbList);
    }


    public void roomSaveOnAction(ActionEvent actionEvent) {

        String roomId = cmbRoomId.getValue();
        String roomType = cmbRoomType.getValue();
        double keyMoney = Double.parseDouble(txtkeymoney.getText());
        int roomQty = Integer.parseInt(txtroomQty.getText());
        RoomDto roomDto = new RoomDto(roomId, roomType, keyMoney, roomQty);
        boolean isSaved = roomBo.saveRoom(roomDto);
        if (isSaved) {
            new Alert(Alert.AlertType.CONFIRMATION, "Save Succesed!").show();
        } else {
            new Alert(Alert.AlertType.NONE, "Try Again").show();
        }


    }

    public void roomUpdateonAction(ActionEvent actionEvent) throws SQLException {
        boolean isUpdated = roomBo.updateRoom(new RoomDto(cmbRoomId.getValue(), cmbRoomType.getValue(), Double.parseDouble(txtkeymoney.getText()
        ), Integer.parseInt(txtroomQty.getText())));
        if (isUpdated == true) {
            new Alert(Alert.AlertType.CONFIRMATION, "Update Succesed!").show();
        } else {
            new Alert(Alert.AlertType.NONE, "Try Again").show();
        }
    }

    public void roomDeleteOnAction(ActionEvent actionEvent) throws SQLException {
        boolean isDeleted = roomBo.deleteRoom(txtroomId.getText());
        if(isDeleted==true){
            new Alert(Alert.AlertType.CONFIRMATION, "Delete Succesed!").show();
        }else{
            new Alert(Alert.AlertType.NONE, "Try Again").show();
        }
    }
}
