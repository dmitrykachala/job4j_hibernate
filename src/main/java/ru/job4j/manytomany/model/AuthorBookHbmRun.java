package ru.job4j.manytomany.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class AuthorBookHbmRun {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            /*Author one = Author.of("Pelevin");
            Author two = Author.of("King");
            Author three = Author.of("Tolstoy");
            Author four = Author.of("Gogol");
            Author five = Author.of("Gorky");

            Book first = Book.of("First");
            first.getAuthors().add(one);
            first.getAuthors().add(two);

            Book second = Book.of("Second");
            second.getAuthors().add(three);
            second.getAuthors().add(four);

            Book blue = Book.of("Blue");
            blue.getAuthors().add(one);
            blue.getAuthors().add(two);
            blue.getAuthors().add(five);

            session.persist(first);
            session.persist(second);
            session.persist(blue);*/

            Book book = session.get(Book.class, 3);
            session.remove(book);

            session.getTransaction().commit();
            session.close();
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
