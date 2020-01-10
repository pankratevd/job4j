package ru.job4j.statistic;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static ru.job4j.statistic.Analyze.Info;
import static ru.job4j.statistic.Analyze.User;

public class AnalyzeTest {

    @Test
    public void test() {
        List<User> prev = new ArrayList<>();
        List<User> current = new ArrayList<>();
        Analyze analyze = new Analyze();
        User u1 = new User(1, "1");
        User u2 = new User(2, "2");
        User u3 = new User(3, "3");
        User u4 = new User(4, "4");
        User u5 = new User(5, "5");
        User u6 = new User(6, "6");
        User u7 = new User(7, "7");
        User u8 = new User(8, "8");
        User u9 = new User(9, "9");

        prev.add(u1);
        prev.add(u2);
        prev.add(u3);
        prev.add(u4);
        prev.add(u5);
        prev.add(u6);

        current.add(u5);
        current.add(u6);
        current.add(u7);
        current.add(u8);
        current.add(u9);

        Info result = analyze.diff(prev, current);
        System.out.println("Added: " + result.getAdded());
        System.out.println("Changed: " + result.getChanged());
        System.out.println("Deleted: " + result.getDeleted());


    }
}
