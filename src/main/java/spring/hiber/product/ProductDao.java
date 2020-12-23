package spring.hiber.product;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ProductDao {
    private static SessionFactory factory;

    public static void init() {

        factory = new Configuration().configure("config/product/hibernate.cfg.xml").buildSessionFactory();
    }

    public static void showManyItems() {
        try (Session session = factory.getCurrentSession()){
            session.beginTransaction();
            List<Product> products = session.createQuery("FROM products.products_tbl").getResultList();
            System.out.println(products + "/n");
        }
    }

    public static void main(String[] args) {
        init();
        showManyItems();
    }
}
