package gleb.server.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.File;
import java.util.Locale;

public abstract class Sess {
    private static Session session;

    public static Session getSess() {
        return session;
    }
    static {
        Locale.setDefault(Locale.ENGLISH);
        String hiberCfgPath = "C:\\Users\\Glebushka\\Documents\\edoC\\HR_monitor\\src\\gleb\\server\\dao\\resources\\hibernate.cfg.xml";
        SessionFactory factory = new Configuration().configure(new File(hiberCfgPath)).buildSessionFactory();
        session = factory.openSession();
    }
}
