package spring.hiber.product;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

@Component // пытался бин созадть, что-то не вышло :(((
public class Factory {
    public static SessionFactory factory;

    public Factory() {
        factory = new Configuration()
                .configure("config/product/hibernate.cfg.xml")
                .buildSessionFactory();
    }

    public void shutdown() {
        factory.close();
    }

    public Session getSession() {
        return factory.getCurrentSession();
    }

}
