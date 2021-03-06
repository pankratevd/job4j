package ru.job4j.map;

import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class UserTest {

    @Test
    public void testToPrint() {
        User u1 = new User("Ivan", 1, new GregorianCalendar(1990, Calendar.FEBRUARY, 1));
        User u2 = new User("Ivan", 1, new GregorianCalendar(1990, Calendar.FEBRUARY, 1));
        Map<User, Object> map = new HashMap<>();
        map.put(u1, 0);
        map.put(u2, 1);
        System.out.println(map); //{ru.job4j.map.User@5f1de56=1}
    }
}
