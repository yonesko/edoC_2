package gleb.server;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Locale;

public abstract class Sess {
    private static SessionFactory factory;
    private static Session session;

    public static Session getSess() {
        return session;
    }

    static {
        Locale.setDefault(Locale.ENGLISH);
        factory = new Configuration().configure().buildSessionFactory();
        session = factory.openSession();
    }
}
