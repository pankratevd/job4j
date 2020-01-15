package ru.job4j.statistic;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static ru.job4j.statistic.Analyze.User;

public class AnalyzeTest {

    @Test
    public void testDiff() {
        List<User> previous = new ArrayList<>();
        List<User> current = new ArrayList<>();

        User u1 = new User(1, "1");
        User u2 = new User(2, "2");
        User u3 = new User(3, "3");
        User u4 = new User(4, "4");
        User u5 = new User(5, "5");
        User u6 = new User(6, "6");
        User u7 = new User(7, "7");
        User u8 = new User(8, "8");
        User u9 = new User(9, "9");
        User u10 = new User(10, "10");
        User u11 = new User(11, "11");
        User u1changed = new User(1, "changed");

        previous.add(u1);
        previous.add(u2);
        previous.add(u3);
        previous.add(u4);
        previous.add(u5);
        previous.add(u6);
        previous.add(u11);

        current.add(u1changed);
        current.add(u5);
        current.add(u6);
        current.add(u7);
        current.add(u8);
        current.add(u9);
        current.add(u10);


        Analyze.Info info = new Analyze().diff(previous, current);

        assertThat(info.getAdded(), is(4)); //u7, u8, u9 , u10
        assertThat(info.getDeleted(), is(4)); //u2, u3, u4, u11
        assertThat(info.getChanged(), is(1)); //u1

    }

}
