package lk.ijse.hostel_management_system.util;

import lk.ijse.hostel_management_system.entity.StudentEntitie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Properties;

public class FactoryConfiguration {
    public static  FactoryConfiguration factoryConfiguration;
    public SessionFactory sessionFactory;

    private FactoryConfiguration(){
            Configuration configuration = new Configuration().configure().addAnnotatedClass(StudentEntitie.class);
            sessionFactory=configuration.buildSessionFactory();



    }
    public static  FactoryConfiguration getInstance(){
         return factoryConfiguration==null ?factoryConfiguration=new FactoryConfiguration(): factoryConfiguration;
    }
    public Session getSession(){
         return sessionFactory.openSession();
    }

}
