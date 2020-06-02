package ru.job4j.tracker;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

public class SqlTracker implements Store {
    private Connection cn;
    private PreparedStatement st = null;
    private ResultSet rs = null;

    private static final Logger LOG = LogManager.getLogger(SqlTracker.class.getName());

    public void init() {
        try (InputStream in = SqlTracker.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            cn = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void close() throws Exception {
        if (cn != null) {
            cn.close();
        }
    }

    @Override
    public Item add(Item item) {
        init();
        item.setId(this.generateId());
        try {
            st = cn.prepareStatement("INSERT INTO task (number, subject, description, created, state_id, author_id) VALUES (? , ? , ? , ? , ? , ?)");
            st.setString(1, item.getId());
            st.setString(2, item.getName());
            st.setString(3, item.getDesc());
            st.setTimestamp(4, new java.sql.Timestamp(System.currentTimeMillis()));
            st.setInt(5, 1);
            st.setInt(6, 1);
            st.executeUpdate();

        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            try {
                st.close();
                close();
            } catch (Exception e) {
                LOG.error(e.getMessage(), e);
            }
        }
        return item;
    }

    @Override
    public boolean replace(String id, Item item) {
        int result = 0;

        init();

        try {
            st = cn.prepareStatement("UPDATE task SET subject = ? , description = ? WHERE number = ?");
            st.setString(1, item.getName());
            st.setString(2, item.getDesc());
            st.setString(3, id);
            result = st.executeUpdate();

        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            try {
                st.close();
                close();
            } catch (Exception e) {
                LOG.error(e.getMessage(), e);
            }
        }
        return result != 0;
    }

    @Override
    public boolean delete(String id) {
        int result = 0;
        init();

        try {
            st = cn.prepareStatement("DELETE FROM task WHERE number = ?");
            st.setString(1, id);
            result = st.executeUpdate();

        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            try {
                st.close();
                close();
            } catch (Exception e) {
                LOG.error(e.getMessage(), e);
            }
        }
        return result != 0;
    }

    @Override
    public List<Item> findAll() {
        List<Item> list = new ArrayList<>();

        init();

        try {
            st = cn.prepareStatement("SELECT * FROM task");
            rs = st.executeQuery();
            while (rs.next()) {
                list.add(new Item(rs.getString("subject"), rs.getString("description"), rs.getString("number")));
            }

        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            try {
                rs.close();
                st.close();
                close();
            } catch (Exception e) {
                LOG.error(e.getMessage(), e);
            }
        }
        return list;
    }

    @Override
    public List<Item> findByName(String key) {
        List<Item> list = new ArrayList<>();
        init();

        try {
            st = cn.prepareStatement("SELECT * FROM task WHERE subject LIKE ?");
            st.setString(1, key);
            rs = st.executeQuery();

            while (rs.next()) {
                list.add(new Item(rs.getString("subject"), rs.getString("description"), rs.getString("number")));
            }

        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            try {
                rs.close();
                st.close();
                close();
            } catch (Exception e) {
                LOG.error(e.getMessage(), e);
            }
        }

        return list;
    }

    @Override
    public Item findById(String id) {
        Item result = null;

        init();

        try {
            st = cn.prepareStatement("SELECT * FROM task WHERE number = ?");
            st.setString(1, id);
            rs = st.executeQuery();
            rs.next();
            result = new Item(rs.getString("subject"), rs.getString("description"), rs.getString("number"));


        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            try {
                rs.close();
                st.close();
                close();
            } catch (Exception e) {
                LOG.error(e.getMessage(), e);
            }
        }

        return result;
    }

    private String generateId() {
        Random rm = new Random();
        return String.valueOf(rm.nextLong() + System.currentTimeMillis());
    }
}
