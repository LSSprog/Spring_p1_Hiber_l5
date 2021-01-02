package spring.hiber.product;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        Factory factory = new Factory();

        ProductDao dao = new ProductDao(factory);

        dao.showManyItems();

        List<Product> allProducts = dao.findAll();
        Product product = dao.findById(3L);
        System.out.println(product);
        dao.saveOrUpdate(new Product("trust", 131));
        dao.deleteById(4L);

        factory.shutdown();

    }
}
