package ru.job4j.integration;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class OrdersStoreTest {

    private static BasicDataSource pool = new BasicDataSource();

    @BeforeClass
    public static void setUp() throws SQLException {
        pool.setDriverClassName("org.hsqldb.jdbcDriver");
        pool.setUrl("jdbc:hsqldb:mem:tests;sql.syntax_pgs=true");
        pool.setUsername("sa");
        pool.setPassword("");
        pool.setMaxTotal(2);
        StringBuilder builder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream("./db/update_001.sql")))
        ) {
            br.lines().forEach(line -> builder.append(line).append(System.lineSeparator()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        pool.getConnection().prepareStatement(builder.toString()).executeUpdate();
    }

    @Test
    public void whenSaveOrderAndFindById() {
        OrdersStore store = new OrdersStore(pool);

        Order saveOrder = store.save(Order.of("nameFind", "descriptionFind"));
        Order loadOrder = store.findById(saveOrder.getId());

        assertThat(loadOrder.getCreated(), is(saveOrder.getCreated()));
        assertThat(loadOrder.getDescription(), is("descriptionFind"));
        assertThat(loadOrder.getName(), is("nameFind"));
    }

    @Test
    public void whenSaveOrderAndFindByName() {
        OrdersStore store = new OrdersStore(pool);

        Order loadOrder = store.findByName("nameFind");

        assertThat(loadOrder.getDescription(), is("descriptionFind"));
    }

    @Test
    public void whenSaveOrderAndUpdateIt() {
        OrdersStore store = new OrdersStore(pool);

        Order saveOrder = store.save(Order.of("nameForSave", "descriptionForSave"));
        Order newOrder = Order.of("newName", "newDescription");
        newOrder.setId(saveOrder.getId());
        store.update(newOrder);
        Order loadOrder = store.findById(saveOrder.getId());

        assertThat(loadOrder.getName(), is("newName"));
        assertThat(loadOrder.getDescription(), is("newDescription"));
    }

    @Test
    public void whenSaveOrderAndFindAllOneRowWithDescription() {
        OrdersStore store = new OrdersStore(pool);

        List<Order> all = (List<Order>) store.findAll();

        Order order1 = all.stream().filter(order -> order.getName().equals("nameFind")).findFirst().get();
        Order order3 = all.stream().filter(order -> order.getName().equals("newName")).findFirst().get();

        assertThat(all.size(), is(2));
        assertThat(order1.getDescription(), is("descriptionFind"));
        assertThat(order3.getDescription(), is("newDescription"));
    }
}
