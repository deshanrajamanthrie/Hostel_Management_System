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
  //  public JFXTextField txtroomId;
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
    public JFXTextField txtRoomId;

    RoomBoImpl roomBo = new RoomBoImpl();

    public void initialize() {
        cmbTypeListner();
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
               txtRoomId.setText(newValue.getRoomId());
                cmbRoomType.setValue(newValue.getRoomType());
                txtkeymoney.setText(String.valueOf(newValue.getKeyMoney()));
                txtroomQty.setText(String.valueOf(newValue.getRoomQty()));
            }
        });

    }

    private void getAllRooms() {
        if(tblroom.getItems().size()>=0){
            tblroom.getItems().clear();

        }
        List<RoomDto> allStudent = roomBo.getAllRoom();

        for (RoomDto r : allStudent) {
            Button btn = new Button("Remove Room");
            tblroom.getItems().add(new RoomTm(r.getRoomId(),
                    r.getRoomType(), r.getKeyMoney(), r.getRoomQty(),
                    btn));

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
    public void cmbTypeListner() {
        ObservableList<String> cmbList = FXCollections.observableArrayList();
        cmbList.add("Non-AC");
        cmbList.add("Non-AC / Food");
        cmbList.add("AC ");
        cmbList.add("AC / Food");
        cmbRoomType.setItems(cmbList);
    }


    public void roomSaveOnAction(ActionEvent actionEvent) {

        String roomId =txtRoomId.getText();
        String roomType = cmbRoomType.getValue();
        double keyMoney = Double.parseDouble(txtkeymoney.getText());
        int roomQty = Integer.parseInt(txtroomQty.getText());
        RoomDto roomDto = new RoomDto(roomId, roomType, keyMoney, roomQty);
        boolean isSaved = roomBo.saveRoom(roomDto);
        if (isSaved) {
            new Alert(Alert.AlertType.CONFIRMATION, "Save Succesed!").show();
            initialize();
            txtRoomId.clear();
            txtkeymoney.clear();
            txtroomQty.clear();
        } else {
            new Alert(Alert.AlertType.NONE, "Try Again").show();
        }
    }
    public void roomUpdateonAction(ActionEvent actionEvent) throws SQLException {
        boolean isUpdated = roomBo.updateRoom(new RoomDto(txtRoomId.getText(),
                cmbRoomType.getValue(), Double.parseDouble(txtkeymoney.getText()
        ), Integer.parseInt(txtroomQty.getText())));
        if (isUpdated == true) {
            new Alert(Alert.AlertType.CONFIRMATION, "Update Succesed!").show();
            initialize();
            txtRoomId.clear();
            txtkeymoney.clear();
            txtroomQty.clear();
        } else {
            new Alert(Alert.AlertType.NONE, "Try Again").show();
        }
    }

    public void roomDeleteOnAction(ActionEvent actionEvent) throws SQLException {
        boolean isDeleted = roomBo.deleteRoom(txtRoomId.getText());
        if(isDeleted==true){
            new Alert(Alert.AlertType.CONFIRMATION, "Delete Succesed!").show();
            initialize();
            txtRoomId.clear();
            txtkeymoney.clear();
            txtroomQty.clear();
        }else{
            new Alert(Alert.AlertType.NONE, "Try Again").show();
        }
    }
}
