package ru.job4j.tracker;

import org.junit.Test;

public class SqlTrackerTest {

    @Test
    public void initTest() {
        SqlTracker sqlTracker = new SqlTracker();
        sqlTracker.init();
    }
}
