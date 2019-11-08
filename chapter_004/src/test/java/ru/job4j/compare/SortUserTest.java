package ru.job4j.compare;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SortUserTest {

    @Test
    public void checkSort() {
        List<User> list = new ArrayList<>();
        SortUser sortUser = new SortUser();
        User user1 = new User("ivan1", 25);
        User user2 = new User("ivan2", 27);
        User user3 = new User("ivan3", 21);
        User user4 = new User("ivan4", 20);
        User user5 = new User("ivan5", 30);
        list.add(user1);
        list.add(user2);
        list.add(user3);
        list.add(user4);
        list.add(user5);
        List<User> expected = List.of(user4, user3, user1, user2, user5);
       /* expected.add(user4);
        expected.add(user3);
        expected.add(user1);
        expected.add(user2);
        expected.add(user5);*/
        Set<User> result = sortUser.sort(list);

        assertThat(expected, is(new ArrayList<>(result)));

    }

    @Test
    public void sortNameLength() {
        List<User> list = new ArrayList<>();
        SortUser sortUser = new SortUser();
        User user1 = new User("Сергей", 25);
        User user2 = new User("СергейСергей", 20);
        User user3 = new User("ИванИван", 30);
        User user4 = new User("Иван", 25);
        list.add(user1);
        list.add(user2);
        list.add(user3);
        list.add(user4);
        List<User> expected = List.of(user4, user1, user3, user2);
        /*expected.add(user4);
        expected.add(user1);
        expected.add(user3);
        expected.add(user2);*/
        List<User> result = sortUser.sortNameLength(list);

        assertThat(expected, is(result));

    }

    @Test
    public void checkSortByAllFields() {
        List<User> list = new ArrayList<>();
        SortUser sortUser = new SortUser();
        User user1 = new User("Сергей", 25);
        User user2 = new User("Иван", 30);
        User user3 = new User("Сергей", 20);
        User user4 = new User("Иван", 25);
        User user5 = new User("Сергей", 20);
        list.add(user1);
        list.add(user2);
        list.add(user3);
        list.add(user4);
        list.add(user5);
        List<User> expected = List.of(user4, user2, user3, user5, user1);
       /* expected.add(user4);
        expected.add(user2);
        expected.add(user3);
        expected.add(user5);
        expected.add(user1);*/
        List<User> result = sortUser.sortByAllFields(list);

        assertThat(expected, is(result));
    }

}
