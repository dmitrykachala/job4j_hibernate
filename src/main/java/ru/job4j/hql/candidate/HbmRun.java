package ru.job4j.hql.candidate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

public class HbmRun {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            Query query = session.createQuery("from Candidate");
            for (Object st : query.list()) {
                System.out.println(st);
            }

            Query query1 = session.createQuery("from Candidate c where c.id = :fId");
            query1.setParameter("fId", 2);
            System.out.println(query1.uniqueResult());

            Query query2 = session.createQuery("from Candidate c where c.name = :fName");
            query2.setParameter("fName", "Nikita");
            System.out.println(query2.list());

            Query query3 = session.createQuery(
                    "update Candidate c set c.experience = :newExperience, "
                            + "c.salary = :newSalary where c.id = :fId"
            );
            query3.setParameter("newExperience", 10);
            query3.setParameter("newSalary", 3500);
            query3.setParameter("fId", 2);
            query3.executeUpdate();

            session.createQuery("delete from Candidate where id = :fId")
                    .setParameter("fId", 1)
                    .executeUpdate();

            Query query4 = session.createQuery("from Candidate");
            for (Object st : query4.list()) {
                System.out.println(st);
            }

            session.getTransaction().commit();
            session.close();
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}

