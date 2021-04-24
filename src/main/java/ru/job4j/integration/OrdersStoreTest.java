package ru.job4j.integration;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.Before;
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

    private BasicDataSource pool = new BasicDataSource();

    @Before
    public void setUp() throws SQLException {
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
    public void whenSaveOrderAndFindAllOneRowWithDescription() {
        OrdersStore store = new OrdersStore(pool);

        store.save(Order.of("name1", "description1"));

        List<Order> all = (List<Order>) store.findAll();

        assertThat(all.size(), is(1));
        assertThat(all.get(0).getDescription(), is("description1"));
        assertThat(all.get(0).getId(), is(1));
    }
    @Test
    public void whenSaveOrderAndFindById() {
        OrdersStore store = new OrdersStore(pool);

        Order saveOrder = store.save(Order.of("name1", "description1"));
        Order loadOrder = store.findById(saveOrder.getId());

        assertThat(loadOrder.getCreated(), is(saveOrder.getCreated()));
        assertThat(loadOrder.getDescription(), is("description1"));
        assertThat(loadOrder.getName(), is("name1"));
    }
    @Test
    public void whenSaveOrderAndFindByName() {
        OrdersStore store = new OrdersStore(pool);

        Order saveOrder = store.save(Order.of("name1", "description1"));
        Order loadOrder = store.findByName("name1");

        assertThat(loadOrder.getCreated(), is(saveOrder.getCreated()));
        assertThat(loadOrder.getId(), is(saveOrder.getId()));
        assertThat(loadOrder.getDescription(), is("description1"));
    }

    @Test
    public void whenSaveOrderAndUpdateIt() {
        OrdersStore store = new OrdersStore(pool);

        Order saveOrder = store.save(Order.of("name1", "description1"));
        Order newOrder = Order.of("NewName", "NewDescription");
        newOrder.setId(saveOrder.getId());
        store.update(newOrder);

        Order loadOrder = store.findById(saveOrder.getId());

        assertThat(loadOrder.getName(), is("NewName"));
        assertThat(loadOrder.getDescription(), is("NewDescription"));
    }
}
