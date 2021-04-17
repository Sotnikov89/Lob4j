package ru.job4j.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class HbmRun {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            List<CarModel> carModels = List.of(new CarModel("Escape"), new CarModel("Focus"),
                    new CarModel("Kuga"), new CarModel("Mondeo"), new CarModel("Mustang"));
            carModels.forEach(session::save);

            CarMake make = new CarMake("Ford");
            session.createQuery("FROM CarModel").list().forEach(model -> make.addCarModel((CarModel) model));
            session.save(make);

            session.getTransaction().commit();
            session.close();
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
