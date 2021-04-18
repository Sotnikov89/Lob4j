package ru.job4j.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.ArrayList;
import java.util.List;

public class HbmRun {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        List<CarMake> list2 = new ArrayList<>();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            /*
            Book book1 = new Book("Двойник");
            Book book2 = new Book("Ночной дозор");
            Book book3 = new Book("Исповедь");

            Author author1 = new Author("Сергей Лукьяненко");
            author1.addBook(book3);
            Author author2 = new Author("Сара Уотерс");
            author2.addBook(book3);
            Author author3 = new Author("Ф.М. Достоевский");
            author3.addBook(book1);
            Author author4 = new Author("Жозе Сарамаго");
            author4.addBook(book1);
            Author author5 = new Author("Лев Толстой");
            author5.addBook(book2);
            Author author6 = new Author("Блаженный Августин");
            author6.addBook(book2);

            Stream.of(author1, author2, author3, author4, author5, author6).forEach(session::persist);
            */

            /*
            List<CarModel> carModels = List.of(new CarModel("Escape"), new CarModel("Focus"),
                    new CarModel("Kuga"), new CarModel("Mondeo"), new CarModel("Mustang"));
            carModels.forEach(session::save);

            CarMake make = new CarMake("Ford");
            session.createQuery("FROM CarModel").list().forEach(model -> make.addCarModel((CarModel) model));
            session.save(make);

            carModels.forEach(make::addCarModel);
            carModels.forEach(carModel -> carModel.setCarMake(make));
            */

            List<CarMake> list1 = session.createQuery("FROM CarMake ").list();
            for (CarModel carModel : list1.get(0).getCarModels()) {
                System.out.println(carModel);
            }
            list2 = session.createQuery("SELECT DISTINCT c from CarMake c join fetch c.carModels").list();

            session.getTransaction().commit();
            session.close();
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
        list2.forEach(carMake -> carMake.getCarModels().forEach(System.out::println));
    }
}
