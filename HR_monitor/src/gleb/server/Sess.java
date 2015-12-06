package gleb.server;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.File;
import java.util.Locale;

public abstract class Sess {
    private static SessionFactory factory;
    private static Session session;

    public static Session getSess() {

        Locale.setDefault(Locale.ENGLISH);
        factory = new Configuration().configure(new File("C:\\Users\\Glebushka\\Documents\\edoC\\HR_monitor\\src\\gleb\\server\\resources\\hibernate.cfg.xml")).buildSessionFactory();
        session = factory.openSession();

        return session;
    }

}
