package ru.job4j.hibernate.date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

public class HbmRun {
    public static void main(String[] args) {
        /*final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf
                    .withOptions()
                    .jdbcTimeZone(TimeZone.getTimeZone("America/Los_Angeles"))
                    .openSession();
            session.beginTransaction();

            Product pr = Product.of("Молоко", "Савушкин продукт");
            session.save(pr);

            session.getTransaction().commit();

            List<Product> products = session.createQuery("from Product").list();
            for (Product prod : products) {
                System.out.println(prod);
            }

            session.close();

        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }*/
        Calendar now = Calendar.getInstance();
        TimeZone timeZone = now.getTimeZone();
        System.out.println("Current TimeZone is : " + timeZone);

    }
}
