package ru.job4j.intgration;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
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

    @After
    public void closeUp() throws SQLException {
        pool.setDriverClassName("org.hsqldb.jdbcDriver");
        pool.setUrl("jdbc:hsqldb:mem:tests;sql.syntax_pgs=true");
        pool.setUsername("sa");
        pool.setPassword("");
        pool.setMaxTotal(2);
        StringBuilder builder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream("./db/update_002.sql")))
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
    public void whenUpdateOrderAndFindByNameAndFindById() {
        OrdersStore store = new OrdersStore(pool);

        Order oldOrder = new Order(1, "old", "oldDescription",
                new Timestamp(System.currentTimeMillis()));

        Order newOrder = new Order(1, "new", "newDescription",
                new Timestamp(System.currentTimeMillis()));

        Order anotherOneOrder = new Order(2, "aoo", "descr",
                new Timestamp(System.currentTimeMillis()));

        store.save(oldOrder);
        store.save(anotherOneOrder);

        store.update(newOrder);

        List<Order> all = (List<Order>) store.findAll();

        List<Order> byName = (List<Order>) store.findByName("new");

        Order byId = store.findById(2);

        assertThat(all.get(0), is(newOrder));
        assertThat(byName.get(0), is(newOrder));
        assertThat(byId, is(anotherOneOrder));
    }

}