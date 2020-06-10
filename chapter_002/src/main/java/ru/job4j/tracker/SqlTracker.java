package ru.job4j.tracker;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SqlTracker implements Store {
    private Connection cn;

    private static final Logger LOG = LogManager.getLogger(SqlTracker.class.getName());

    public SqlTracker(Connection cn) {
        this.cn = cn;
    }

    public SqlTracker() {
    }

    public void init() {
        if (cn == null) {
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
    }

    @Override
    public void close() throws Exception {
        if (cn != null) {
            cn.close();
        }
    }

    @Override
    public Item add(Item item) {

        try (PreparedStatement st = cn.prepareStatement("INSERT INTO task (subject, description, created) VALUES (? , ? , ?)", Statement.RETURN_GENERATED_KEYS)) {
            st.setString(1, item.getName());
            st.setString(2, item.getDesc());
            st.setTimestamp(3, new java.sql.Timestamp(System.currentTimeMillis()));
            st.executeUpdate();

            try (ResultSet rs = st.getGeneratedKeys()) {
                if (rs.next()) {
                    item.setId(String.valueOf(st.getGeneratedKeys().getLong("id")));
                }
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return item;
    }

    @Override
    public boolean replace(String id, Item item) {
        int result = 0;

        try (PreparedStatement st = cn.prepareStatement("UPDATE task SET subject = ? , description = ? WHERE id = ?")) {
            st.setString(1, item.getName());
            st.setString(2, item.getDesc());
            st.setInt(3, Integer.parseInt(id));
            result = st.executeUpdate();

        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }

        return result != 0;
    }

    @Override
    public boolean delete(String id) {
        int result = 0;

        try (PreparedStatement st = cn.prepareStatement("DELETE FROM task WHERE id = ?")) {
            st.setInt(1, Integer.parseInt(id));
            result = st.executeUpdate();

        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }

        return result != 0;
    }

    @Override
    public List<Item> findAll() {
        List<Item> list = new ArrayList<>();

        try (PreparedStatement st = cn.prepareStatement("SELECT * FROM task")) {

            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    list.add(new Item(rs.getString("subject"), rs.getString("description"), rs.getString("id")));
                }
            }

        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }

        return list;
    }

    @Override
    public List<Item> findByName(String key) {
        List<Item> list = new ArrayList<>();

        try (PreparedStatement st = cn.prepareStatement("SELECT * FROM task WHERE subject LIKE ?")) {
            st.setString(1, key);

            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    list.add(new Item(rs.getString("subject"), rs.getString("description"), rs.getString("id")));
                }
            }

        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }

        return list;
    }

    @Override
    public Item findById(String id) {
        Item result = null;

        try (PreparedStatement st = cn.prepareStatement("SELECT * FROM task WHERE id = ?");) {
            st.setInt(1, Integer.parseInt(id));

            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    result = new Item(rs.getString("subject"), rs.getString("description"), rs.getString("id"));
                }
            }

        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }

        return result;
    }
}
