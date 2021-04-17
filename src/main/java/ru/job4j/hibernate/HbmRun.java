package ru.job4j.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.stream.Stream;

public class HbmRun {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
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
            session.remove(session.get(Author.class, 1));

            session.getTransaction().commit();
            session.close();
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
