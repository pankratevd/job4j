package ru.job4j.tracker;

import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class TrackerTest {
    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1");
        tracker.add(item);
        Item result = tracker.findById(item.getId());
        assertThat(result.getName(), is(item.getName()));
    }

    @Test
    public void whenReplaceNameThenReturnNewName() {
        Tracker tracker = new Tracker();
        Item previous = new Item("test1");
        // Добавляем заявку в трекер. Теперь в объект проинициализирован id.
        tracker.add(previous);
        // Создаем новую заявку.
        Item next = new Item("test2");
        // Проставляем старый id из previous, который был сгенерирован выше.
        next.setId(previous.getId());
        // Обновляем заявку в трекере.
        tracker.replace(previous.getId(), next);
        // Проверяем, что заявка с таким id имеет новые имя test2.
        assertThat(tracker.findById(previous.getId()).getName(), is("test2"));
    }

    @Test
    public void whenFindDeleteIsTrue() {
        Tracker tracker = new Tracker();
        Item item1 = new Item("task1");
        Item item2 = new Item("task2");
        Item item3 = new Item("task3");
        Item item4 = new Item("task4");
        Item item5 = new Item("task5");
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);
        tracker.add(item4);
        tracker.add(item5);
        Item[] expected = {item1, item2, item4, item5};
        assertThat(tracker.delete(item3.getId()), is(true));
    }

    @Test
    public void whenFindDeleteIsFalse() {
        Tracker tracker = new Tracker();
        Item item1 = new Item("task1");
        Item item2 = new Item("task2");
        Item item3 = new Item("task3");
        Item item4 = new Item("task4");
        Item item5 = new Item("task5");
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);
        tracker.add(item4);
        tracker.add(item5);
        Item[] expected = {item2, item3, item4, item5};
        assertThat(tracker.delete("wrongId"), is(false));
    }

    @Test
    public void whenFindAll() {
        Tracker tracker = new Tracker();
        Item item1 = new Item("task1");
        Item item2 = new Item("task2");
        Item item3 = new Item("task3");
        Item item4 = new Item("task4");
        Item item5 = new Item("task5");
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);
        tracker.add(item4);
        tracker.add(item5);
        ArrayList<Item> expected = new ArrayList<>();
        expected.add(item1);
        expected.add(item2);
        expected.add(item3);
        expected.add(item4);
        expected.add(item5);
        assertThat(tracker.findAll(), is(expected));
    }

    @Test
    public void findByNameWhenExist() {
        Tracker tracker = new Tracker();
        Item item1 = new Item("task1");
        Item item2 = new Item("task2");
        Item item3 = new Item("task1");
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);
        ArrayList<Item> expected = new ArrayList<>();
        expected.add(item1);
        expected.add(item3);
        assertThat(expected, is(tracker.findByName("task1")));
    }

    @Test
    public void findByNameWhenNotExist() {
        Tracker tracker = new Tracker();
        Item item1 = new Item("task1");
        Item item2 = new Item("task2");
        Item item3 = new Item("task1");
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);
        ArrayList<Item> expected = new ArrayList<>();
        assertThat(expected, is(tracker.findByName("task3")));
    }

    @Test
    public void findByIdWhenExist() {
        Tracker tracker = new Tracker();
        Item item1 = new Item("task1");
        Item item2 = new Item("task2");
        Item item3 = new Item("task1");
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);
        String id = item2.getId();
        assertThat(item2, is(tracker.findById(id)));
    }

    @Test
    public void findBydWhenNotExist() {
        Tracker tracker = new Tracker();
        Item item1 = new Item("task1");
        Item item2 = new Item("task2");
        Item item3 = new Item("task1");
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);
        assertThat(null, is(tracker.findById("wrongID")));
    }


    @Test
    public void chainAllOperations() {
        Tracker tracker = new Tracker();
        ArrayList<Item> expected = new ArrayList<>();
        assertThat(expected, is(tracker.findAll()));

        Item item1 = new Item("task1");
        Item item2 = new Item("task2");
        Item item2Replaced = new Item("task2Replaced");
        Item item3 = new Item("task3");
        tracker.add(item1);
        tracker.delete(item1.getId());
        assertThat(expected, is(tracker.findAll()));

        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);
        tracker.replace(item2.getId(), item2Replaced);
        ArrayList<Item> expected10 = new ArrayList<>();
        expected10.add(item1);
        expected10.add(item2Replaced);
        expected10.add(item3);
        assertThat(tracker.findAll(), is(expected10));

        Item item4 = new Item("task4");
        tracker.add(item4);
        ArrayList<Item> expected20 = new ArrayList<>();
        expected20.add(item1);
        expected20.add(item2Replaced);
        expected20.add(item3);
        expected20.add(item4);
        assertThat(expected20, is(tracker.findAll()));

        tracker.delete(item3.getId());
        ArrayList<Item> expected2 = new ArrayList<>();
        expected2.add(item1);
        expected2.add(item2Replaced);
        expected2.add(item4);
        assertThat(expected2, is(tracker.findAll()));

        Item item1dublicate = new Item("task1");
        tracker.add(item1dublicate);

        ArrayList<Item> expected30 = new ArrayList<>();
        expected30.add(item1);
        expected30.add(item1dublicate);
        assertThat(expected30, is(tracker.findByName("task1")));

        Item item5 = new Item("task5");
        tracker.add(item5);
        ArrayList<Item> expected40 = new ArrayList<>();
        expected40.add(item1);
        expected40.add(item2Replaced);
        expected40.add(item4);
        expected40.add(item1dublicate);
        expected40.add(item5);
        assertThat(expected40, is(tracker.findAll()));

        tracker.delete(item5.getId());
        ArrayList<Item> expected50 = new ArrayList<>();
        expected50.add(item1);
        expected50.add(item2Replaced);
        expected50.add(item4);
        expected50.add(item1dublicate);
        assertThat(expected50, is(tracker.findAll()));

        Item item6 = new Item("task6");
        tracker.add(item6);
        ArrayList<Item> expected60 = new ArrayList<>();
        expected60.add(item1);
        expected60.add(item2Replaced);
        expected60.add(item4);
        expected60.add(item1dublicate);
        expected60.add(item6);
        assertThat(expected60, is(tracker.findAll()));
    }

}
