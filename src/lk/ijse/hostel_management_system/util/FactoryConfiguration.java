package lk.ijse.hostel_management_system.util;

import lk.ijse.hostel_management_system.entity.Reserve;
import lk.ijse.hostel_management_system.entity.Room;
import lk.ijse.hostel_management_system.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.Properties;

public class FactoryConfiguration {
    public static  FactoryConfiguration factoryConfiguration;
    public SessionFactory sessionFactory;

    private FactoryConfiguration(){
        Properties properties = new Properties();
        try {
            properties.load(ClassLoader.getSystemClassLoader().getResourceAsStream("lk/ijse/hostel_management_system/resourses/hibernate.properties"));

        } catch (IOException e) {
            e.printStackTrace();
        }
        Configuration configuration = new Configuration().addAnnotatedClass(Student.class).addAnnotatedClass(Room.class).
                addAnnotatedClass(Reserve.class);
        configuration.addProperties(properties);
        sessionFactory=configuration.buildSessionFactory();
    }
    public static  FactoryConfiguration getInstance(){
         return factoryConfiguration==null ?factoryConfiguration=new FactoryConfiguration(): factoryConfiguration;
    }
    public Session getSession(){
         return sessionFactory.openSession();
    }

}
