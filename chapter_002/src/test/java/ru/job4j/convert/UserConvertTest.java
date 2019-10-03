package ru.job4j.convert;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class UserConvertTest {
    @Test
    public void convert3UsersToMap() {
        UserConvert userConvert = new UserConvert();
        List<User> list = new ArrayList<>();
        User user1 = new User(1, "ivan1", "city1");
        User user2 = new User(2, "ivan2", "city2");
        User user3 = new User(3, "ivan3", "city3");
        list.add(user1);
        list.add(user2);
        list.add(user3);
        HashMap<Integer, User> result = userConvert.process(list);
        HashMap<Integer, User> expected = new HashMap<>();
        expected.put(3, user3);
        expected.put(2, user2);
        expected.put(1, user1);
        assertThat(result, is(expected));
    }
}
