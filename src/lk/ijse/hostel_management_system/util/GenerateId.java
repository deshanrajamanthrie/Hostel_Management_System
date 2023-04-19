package lk.ijse.hostel_management_system.util;

public class GenerateId {
    public static  String genarateNewId(String start,String id){
        if(id!=null) {
            String[] split = id.split(start);
            int i = Integer.parseInt(split[1]) + 1;
            return String.format(start + "%04d", i);
        }else {
            return start+"0001";
        }
    }
}
