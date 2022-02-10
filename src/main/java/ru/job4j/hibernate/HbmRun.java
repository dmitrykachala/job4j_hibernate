package ru.job4j.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.hibernate.model.Brand;
import ru.job4j.hibernate.model.Model;

public class HbmRun {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            Model one = Model.of("Polo sedan");
            session.save(one);

            Model two = Model.of("Passat");
            session.save(two);

            Model three = Model.of("Golf");
            session.save(three);

            Model four = Model.of("Multivan");
            session.save(four);

            Model five = Model.of("Tiguan");
            session.save(five);

            Brand vw = Brand.of("VW");
            vw.addModel(session.load(Model.class, 1));
            vw.addModel(session.load(Model.class, 2));
            vw.addModel(session.load(Model.class, 3));
            vw.addModel(session.load(Model.class, 4));
            vw.addModel(session.load(Model.class, 5));

            session.save(vw);

            session.getTransaction().commit();
            session.close();
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
