package spring.hiber.product;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductDao {
    private static Factory factory; //почему я здесь статик написал?...

    @Autowired // пытался проинжектить сюда фабрику - чего то не вышло :(((
    public void setFactory(Factory factory) {
        this.factory = factory;
    }

    public ProductDao(Factory factory) {
        this.setFactory(factory);
    }

    public void showManyItems() {
        try (Session session = factory.getSession()) { //getCurrentSession()){
            session.beginTransaction();
            List<Product> products = session.createQuery("FROM Product").getResultList();
            System.out.println(products + "/n");
        }
    }

    public Product findById(Long id) {
        try (Session session = factory.getSession()) { //getCurrentSession()){
            session.beginTransaction();
            //Product pr1 = (Product) session.byId(String.valueOf(id)); // это ищет по id???
            Product pr2 = session.get(Product.class, id); // или это ???
           /* Product pr = session.createQuery(String.format(
                    "SELECT p FROM products_tbl WHERE p.id = %d", id), Product.class)
                    .getSingleResult(); // через запрос поиск*/
            return pr2; // pr1 pr можно попробовать вернтуь

        }

    }

    public List<Product> findAll() {
        try (Session session = factory.getSession()) { //getCurrentSession()){
            session.beginTransaction();
            List<Product> products = session.createQuery("FROM Product").getResultList();
            return products;
        }
    }

    public void deleteById(Long id) {
        try (Session session = factory.getSession()) { //getCurrentSession()){
            session.beginTransaction();
            Product product = session.get(Product.class, id);
            session.delete(product); // или здесь можно написать запроц query на удаление, чтобы оъект не вытаскивать (из лекции)
            //session.createQuery(String.format("DELETE FROM Product WHERE id = %d", id));
            session.getTransaction().commit();
        }

    }

    public void saveOrUpdate (Product product) {
        try (Session session = factory.getSession()) { //getCurrentSession()){
            session.beginTransaction();
            session.saveOrUpdate(product);
            session.getTransaction().commit();
        }
    }
}
