package lk.ijse.hostel_management_system.db;

import lk.ijse.hostel_management_system.modle.User;
import lk.ijse.hostel_management_system.util.security.PassewordManage;

import java.util.ArrayList;

public class Tmdatabase {
    public static ArrayList<User> usertable = new ArrayList<>();

    static {
        usertable.add(new User("Deshan","Maduranga","deshan@gmail.com",new PassewordManage().encode("1234")));
    }
}
