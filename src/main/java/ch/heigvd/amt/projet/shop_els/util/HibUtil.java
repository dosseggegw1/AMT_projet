package ch.heigvd.amt.projet.shop_els.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.type.StringType;

public class HibUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            return new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        }
        catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}

//TODO NGY remove commented code before review
/*
-> /!\ Config supposée permettre l'utilisation de "GROUP_CONCAT()" dans une requête HQL
-> Empêche la connexion à la BD ...
private static SessionFactory sessionFactory;
static {
    Configuration conf= new Configuration();
    conf.configure();
    conf.addSqlFunction("group_concat", new StandardSQLFunction("group_concat", new StringType()));
    sessionFactory = conf.buildSessionFactory();
}
 */