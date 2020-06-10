package ru.job4j.tracker;

import org.junit.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SqlTrackerTest {

    public Connection init() {
        try (InputStream in = SqlTracker.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            return DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")

            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Test
    public void createItem() throws Exception {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            tracker.add(new Item("name", "desc"));
            assertThat(tracker.findByName("name").size(), is(1));
        }
    }

    @Test
    public void findItemsByName() throws Exception {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            tracker.add(new Item("name", "desc"));
            tracker.add(new Item("name", "desc1"));
            tracker.add(new Item("name2", "desc"));
            assertThat(tracker.findByName("name").size(), is(2));
            assertThat(tracker.findByName("name2").size(), is(1));
        }
    }

    @Test
    public void findItemById() throws Exception {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            String id = tracker.add(new Item("name", "desc")).getId();
            tracker.add(new Item("name1", "desc1"));
            assertThat(tracker.findById(id).getName(), is("name"));
        }
    }


    @Test
    public void replaceItem() throws Exception {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            String id = tracker.add(new Item("name", "desc")).getId();
            tracker.replace(id, new Item("name_1", "desc_1"));
            assertThat(tracker.findByName("name").size(), is(0));
            assertThat(tracker.findByName("name_1").size(), is(1));
        }
    }

    @Test
    public void deleteItem() throws Exception {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            String id = tracker.add(new Item("name", "desc")).getId();
            assertThat(tracker.findByName("name").size(), is(1));
            tracker.delete(id);
            assertThat(tracker.findByName("name").size(), is(0));
        }
    }

    @Test
    public void findAllItems() throws Exception {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            tracker.add(new Item("name", "desc"));
            tracker.add(new Item("name1", "desc1"));
            assertThat(tracker.findAll().size(), is(2));
        }
    }


    @Test
    public void initTest() {
        SqlTracker sqlTracker = new SqlTracker();
        sqlTracker.init();
    }


}
