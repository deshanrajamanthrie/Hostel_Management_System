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
import lk.ijse.hostel_management_system.bo.BOFactory;
import lk.ijse.hostel_management_system.bo.BOTypes;
import lk.ijse.hostel_management_system.bo.SuperBO;
import lk.ijse.hostel_management_system.bo.custom.RoomBO;
import lk.ijse.hostel_management_system.bo.custom.impl.RoomBoImpl;
import lk.ijse.hostel_management_system.dao.DAOFactory;
import lk.ijse.hostel_management_system.dao.DAOType;
import lk.ijse.hostel_management_system.dao.SuperDao;
import lk.ijse.hostel_management_system.dto.RoomDto;
import lk.ijse.hostel_management_system.entity.Room;
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

    RoomBO roomBo = (RoomBO) BOFactory.getInstances().getBo(BOTypes.ROOM);

    //RoomBoImpl roomBo = new RoomBoImpl();
    public void initialize() {
        showTableData();
        cmbTypeListner();
        try {
            getAllRooms();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        tblroom.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                txtRoomId.setText(newValue.getRoomId());
                // System.out.println(newValue.getRoomId());
                cmbRoomType.setValue(newValue.getRoomType());
                //System.out.println(newValue.getRoomType());
                txtkeymoney.setText(String.valueOf(newValue.getKeyMoney()));
                txtroomQty.setText(String.valueOf(newValue.getRoomQty()));
            }
        });

    }

    private void showTableData() {
        colRoomId.setCellValueFactory(new PropertyValueFactory<>("room_id"));
        colRoomType.setCellValueFactory(new PropertyValueFactory<>("room_type"));
        colKeyMoney.setCellValueFactory(new PropertyValueFactory<>("keymoney"));
        colRoomQty.setCellValueFactory(new PropertyValueFactory<>("room_qty"));
        colOperator.setCellValueFactory(new PropertyValueFactory<>("btn"));


    }
    private void getAllRooms() throws SQLException {
        if (tblroom.getItems().size() >= 0) {
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
        String roomId = txtRoomId.getText();
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
        if (isDeleted == true) {
            new Alert(Alert.AlertType.CONFIRMATION, "Delete Succesed!").show();
            initialize();
            txtRoomId.clear();
            txtkeymoney.clear();
            txtroomQty.clear();
        } else {
            new Alert(Alert.AlertType.NONE, "Try Again").show();
        }
    }

    public void roomSearchOnAction(ActionEvent actionEvent) {
        search();

    }

    private void search() {
        Room room = roomBo.searchRoom(txtRoomId.getText());
        if(room.getRoom_id().equals(room)){
            //cmbRoomType.setValue(room.getRoom_type().toString());
            //cmbRoomType.setValue(room.getRoom_type());
            cmbRoomType.setValue(room.getRoom_type());
            txtkeymoney.setText(String.valueOf(room.getKeyMoney()));
            txtroomQty.setText(String.valueOf(room.getRoom_qty()));
        }else{
            new Alert(Alert.AlertType.INFORMATION,"Empty Room").show();
        }
        tblroom.refresh();

    }

    public void roomIdOnaction(ActionEvent actionEvent) {
        search();
    }
}
