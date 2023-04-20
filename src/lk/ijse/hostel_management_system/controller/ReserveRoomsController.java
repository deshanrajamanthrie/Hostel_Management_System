package lk.ijse.hostel_management_system.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import lk.ijse.hostel_management_system.bo.custom.impl.ReserveBoImpl;
import lk.ijse.hostel_management_system.bo.custom.impl.RoomBoImpl;
import lk.ijse.hostel_management_system.bo.custom.impl.StudentBoImpl;
import lk.ijse.hostel_management_system.dto.ReserveDto;
import lk.ijse.hostel_management_system.dto.RoomDto;
import lk.ijse.hostel_management_system.dto.StudentDto;
import lk.ijse.hostel_management_system.entity.Student;
import org.hibernate.annotations.common.util.StringHelper;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class ReserveRoomsController {


    public JFXComboBox<String> cmbStId;
    public JFXTextField txtStName;
    public JFXTextField txtStAddress;
    public JFXTextField txtStDob;
    public JFXTextField txtContactNo;
    public JFXComboBox<String> txtRoomId;
    public JFXTextField txtRoomType;
    public JFXTextField txtRoomAvailable;
    public JFXTextField txtKeymoney;
    public JFXTextField txtPaid;
    public DatePicker datepicker;
    public Label txtgetTime;
    public JFXComboBox<String> cmbStatus;
    public TableView<ReserveDto> tblReserve;
    public TableColumn colReserveId;
    public TableColumn colStartdate;
    public TableColumn colReservedate;
    public TableColumn colstatus;
    public TableColumn colRoomId;
    public TableColumn colStudentid;
    public TableColumn Operator;

    ReserveBoImpl reserveBo = new ReserveBoImpl();
    StudentBoImpl studentBo = new StudentBoImpl();
    RoomBoImpl roomBo = new RoomBoImpl();

    public void initialize() {
        setStatus();
        dateSet();
        //   setRoomDetail();
        setStudentIdAndRoomId();
        cmbStId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->
                setStudentDetail((String) newValue));

        txtRoomId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->
                setRoomDetail((String) newValue));

    }

    public void setStatus() {
        ObservableList<String> statusList = FXCollections.observableArrayList();
        statusList.add("Paid");
        statusList.add("None Paid");
        cmbStatus.setItems(statusList);

    }

    private void dateSet() {
        txtgetTime.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
    }

    List<RoomDto> allroom = roomBo.getAllRoom();
    List<StudentDto> allStudent1 = studentBo.getAllStudent();

    private void setStudentIdAndRoomId() {

        ObservableList<String> stIdList = FXCollections.observableArrayList();
        ObservableList<String> roomList = FXCollections.observableArrayList();
        for (RoomDto r : allroom) {
            roomList.add(r.getRoom_id());
        }
        for (StudentDto s : allStudent1) {
            stIdList.add(s.getStudentId());
        }
        cmbStId.setItems(stIdList);
        txtRoomId.setItems(roomList);
    }

    private void setStudentDetail(String selectedStudentId) {
        for (StudentDto s : allStudent1) {
            if (s.getStudentId().equals(cmbStId.getValue())) {
                txtStName.setText(s.getStudent_name());
                txtStAddress.setText(s.getStudent_address());
                txtStDob.setText(s.getDob());
                txtContactNo.setText(String.valueOf(s.getStudent_contact()));
            }
        }
    }

    private void setRoomDetail(String selectedRoomId) {
        for (RoomDto r : allroom) {
            if (r.getRoom_id().equals(txtRoomId.getValue())) {
                txtRoomType.setText(r.getRoom_type());
                txtRoomAvailable.setText(String.valueOf(r.getRoom_qty()));
                txtKeymoney.setText(String.valueOf(r.getKeymoney()));
            }

        }
    }

    public void placeOrderOnAction(ActionEvent actionEvent) {
        String s = reserveBo.genarateIdReservation();
        try {
            ReserveDto reserveDto = new ReserveDto();
            reserveDto.setReserve_Id(s);
            reserveDto.setStart_Date(txtgetTime.getText());
            reserveDto.setReserve_Date(datepicker.getValue());
            reserveDto.setStatus(cmbStatus.getValue());
            StudentDto studentDto = new StudentDto();
            studentDto.setStudentId(cmbStId.getValue());
            RoomDto roomDto = new RoomDto();
            roomDto.setRoom_id(txtRoomId.getValue());
            reserveDto.setRoom(roomDto);
            reserveDto.setStudent(studentDto);
            reserveBo.saveReserve(reserveDto);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
