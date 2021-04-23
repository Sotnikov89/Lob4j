package ru.job4j.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.util.List;

public class HbmRun {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            //session.save(Candidate.builder().name("Alex").experience(1).salary(100000).build());
            //session.save(Candidate.builder().name("Ben").experience(3).salary(220000).build());
            //session.save(Candidate.builder().name("Howard").experience(5).salary(300000).build());

            /*
            Query getAll = session.createQuery("from Candidate");
            for (Object candidate : getAll.list()) {
                System.out.println(candidate);
            }

            Query getByName = session.createQuery("from Candidate c where c.name = :name");
            getByName.setParameter("name", "Ben");
            System.out.println(getByName.uniqueResult());

            Query getById = session.createQuery("from Candidate c where c.id = :id");
            getById.setParameter("id", 1);
            System.out.println(getById.uniqueResult());

            Query updateById = session.createQuery("update Candidate c " +
                    "set c.name = :name, c.experience = :exp, c.salary = :sal where c.id = :id");
            updateById.setParameter("name", "newName");
            updateById.setParameter("exp", 10);
            updateById.setParameter("sal", 500000);
            updateById.setParameter("id", 1);
            updateById.executeUpdate();
            System.out.println(session.createQuery("from Candidate c where c.id = 1").uniqueResult());

            Query deleteById = session.createQuery("delete Candidate c where c.id = :id");
            deleteById.setParameter("id", 3);
            deleteById.executeUpdate();
            for (Object candidate : session.createQuery("from Candidate").list()) {
                System.out.println(candidate);
            }
            */
            /*
            Candidate can1 = (Candidate) session.createQuery("from Candidate c where c.id = 1").uniqueResult();
            Candidate can2 = (Candidate) session.createQuery("from Candidate c where c.id = 2").uniqueResult();
            Candidate can3 = (Candidate) session.createQuery("from Candidate c where c.id = 3").uniqueResult();

            VacanciesBase base = new VacanciesBase();
            session.save(base);
            Vacancy vacancy1 = Vacancy.builder().name("Little middle").averageSal(100000).minimumExp(1).candidate(can1).vacanciesBase(base).build();
            session.save(vacancy1);
            Vacancy vacancy2 = Vacancy.builder().name("Middle").averageSal(200000).minimumExp(2).candidate(can2).vacanciesBase(base).build();
            session.save(vacancy2);
            Vacancy vacancy3 = Vacancy.builder().name("Senior").averageSal(300000).minimumExp(5).candidate(can3).vacanciesBase(base).build();
            session.save(vacancy3);
            base.getVacancies().addAll(List.of(vacancy1, vacancy2, vacancy3));
            can1.setVacancy(vacancy1);
            can2.setVacancy(vacancy2);
            can3.setVacancy(vacancy3);
            */

            session.getTransaction().commit();
            session.close();

        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
