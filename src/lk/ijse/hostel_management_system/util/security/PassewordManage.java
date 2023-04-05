package lk.ijse.hostel_management_system.util.security;

import org.mindrot.BCrypt;

public class PassewordManage {
    public String encode(String rawpasseWord) {
        String hashpw = BCrypt.hashpw(rawpasseWord, BCrypt.gensalt(10));
        return hashpw;
    }

    public boolean chekpasseword(String rawpasseWord, String hashpasseword) {
        return BCrypt.checkpw(rawpasseWord, hashpasseword);
    }
}
