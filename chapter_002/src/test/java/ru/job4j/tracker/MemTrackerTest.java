package ru.job4j.tracker;

import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class MemTrackerTest {
    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        MemTracker memTracker = new MemTracker();
        Item item = new Item("test1");
        memTracker.add(item);
        Item result = memTracker.findById(item.getId());
        assertThat(result.getName(), is(item.getName()));
    }

    @Test
    public void whenReplaceNameThenReturnNewName() {
        MemTracker memTracker = new MemTracker();
        Item previous = new Item("test1");
        // Добавляем заявку в трекер. Теперь в объект проинициализирован id.
        memTracker.add(previous);
        // Создаем новую заявку.
        Item next = new Item("test2");
        // Проставляем старый id из previous, который был сгенерирован выше.
        next.setId(previous.getId());
        // Обновляем заявку в трекере.
        memTracker.replace(previous.getId(), next);
        // Проверяем, что заявка с таким id имеет новые имя test2.
        assertThat(memTracker.findById(previous.getId()).getName(), is("test2"));
    }

    @Test
    public void whenFindDeleteIsTrue() {
        MemTracker memTracker = new MemTracker();
        Item item1 = new Item("task1");
        Item item2 = new Item("task2");
        Item item3 = new Item("task3");
        Item item4 = new Item("task4");
        Item item5 = new Item("task5");
        memTracker.add(item1);
        memTracker.add(item2);
        memTracker.add(item3);
        memTracker.add(item4);
        memTracker.add(item5);
        Item[] expected = {item1, item2, item4, item5};
        assertThat(memTracker.delete(item3.getId()), is(true));
    }

    @Test
    public void whenFindDeleteIsFalse() {
        MemTracker memTracker = new MemTracker();
        Item item1 = new Item("task1");
        Item item2 = new Item("task2");
        Item item3 = new Item("task3");
        Item item4 = new Item("task4");
        Item item5 = new Item("task5");
        memTracker.add(item1);
        memTracker.add(item2);
        memTracker.add(item3);
        memTracker.add(item4);
        memTracker.add(item5);
        Item[] expected = {item2, item3, item4, item5};
        assertThat(memTracker.delete("wrongId"), is(false));
    }

    @Test
    public void whenFindAll() {
        MemTracker memTracker = new MemTracker();
        Item item1 = new Item("task1");
        Item item2 = new Item("task2");
        Item item3 = new Item("task3");
        Item item4 = new Item("task4");
        Item item5 = new Item("task5");
        memTracker.add(item1);
        memTracker.add(item2);
        memTracker.add(item3);
        memTracker.add(item4);
        memTracker.add(item5);
        ArrayList<Item> expected = new ArrayList<>();
        expected.add(item1);
        expected.add(item2);
        expected.add(item3);
        expected.add(item4);
        expected.add(item5);
        assertThat(memTracker.findAll(), is(expected));
    }

    @Test
    public void findByNameWhenExist() {
        MemTracker memTracker = new MemTracker();
        Item item1 = new Item("task1");
        Item item2 = new Item("task2");
        Item item3 = new Item("task1");
        memTracker.add(item1);
        memTracker.add(item2);
        memTracker.add(item3);
        ArrayList<Item> expected = new ArrayList<>();
        expected.add(item1);
        expected.add(item3);
        assertThat(expected, is(memTracker.findByName("task1")));
    }

    @Test
    public void findByNameWhenNotExist() {
        MemTracker memTracker = new MemTracker();
        Item item1 = new Item("task1");
        Item item2 = new Item("task2");
        Item item3 = new Item("task1");
        memTracker.add(item1);
        memTracker.add(item2);
        memTracker.add(item3);
        ArrayList<Item> expected = new ArrayList<>();
        assertThat(expected, is(memTracker.findByName("task3")));
    }

    @Test
    public void findByIdWhenExist() {
        MemTracker memTracker = new MemTracker();
        Item item1 = new Item("task1");
        Item item2 = new Item("task2");
        Item item3 = new Item("task1");
        memTracker.add(item1);
        memTracker.add(item2);
        memTracker.add(item3);
        String id = item2.getId();
        assertThat(item2, is(memTracker.findById(id)));
    }

    @Test
    public void findBydWhenNotExist() {
        MemTracker memTracker = new MemTracker();
        Item item1 = new Item("task1");
        Item item2 = new Item("task2");
        Item item3 = new Item("task1");
        memTracker.add(item1);
        memTracker.add(item2);
        memTracker.add(item3);
        assertThat(null, is(memTracker.findById("wrongID")));
    }


    @Test
    public void chainAllOperations() {
        MemTracker memTracker = new MemTracker();
        ArrayList<Item> expected = new ArrayList<>();
        assertThat(expected, is(memTracker.findAll()));

        Item item1 = new Item("task1");
        Item item2 = new Item("task2");
        Item item2Replaced = new Item("task2Replaced");
        Item item3 = new Item("task3");
        memTracker.add(item1);
        memTracker.delete(item1.getId());
        assertThat(expected, is(memTracker.findAll()));

        memTracker.add(item1);
        memTracker.add(item2);
        memTracker.add(item3);
        memTracker.replace(item2.getId(), item2Replaced);
        ArrayList<Item> expected10 = new ArrayList<>();
        expected10.add(item1);
        expected10.add(item2Replaced);
        expected10.add(item3);
        assertThat(memTracker.findAll(), is(expected10));

        Item item4 = new Item("task4");
        memTracker.add(item4);
        ArrayList<Item> expected20 = new ArrayList<>();
        expected20.add(item1);
        expected20.add(item2Replaced);
        expected20.add(item3);
        expected20.add(item4);
        assertThat(expected20, is(memTracker.findAll()));

        memTracker.delete(item3.getId());
        ArrayList<Item> expected2 = new ArrayList<>();
        expected2.add(item1);
        expected2.add(item2Replaced);
        expected2.add(item4);
        assertThat(expected2, is(memTracker.findAll()));

        Item item1dublicate = new Item("task1");
        memTracker.add(item1dublicate);

        ArrayList<Item> expected30 = new ArrayList<>();
        expected30.add(item1);
        expected30.add(item1dublicate);
        assertThat(expected30, is(memTracker.findByName("task1")));

        Item item5 = new Item("task5");
        memTracker.add(item5);
        ArrayList<Item> expected40 = new ArrayList<>();
        expected40.add(item1);
        expected40.add(item2Replaced);
        expected40.add(item4);
        expected40.add(item1dublicate);
        expected40.add(item5);
        assertThat(expected40, is(memTracker.findAll()));

        memTracker.delete(item5.getId());
        ArrayList<Item> expected50 = new ArrayList<>();
        expected50.add(item1);
        expected50.add(item2Replaced);
        expected50.add(item4);
        expected50.add(item1dublicate);
        assertThat(expected50, is(memTracker.findAll()));

        Item item6 = new Item("task6");
        memTracker.add(item6);
        ArrayList<Item> expected60 = new ArrayList<>();
        expected60.add(item1);
        expected60.add(item2Replaced);
        expected60.add(item4);
        expected60.add(item1dublicate);
        expected60.add(item6);
        assertThat(expected60, is(memTracker.findAll()));
    }

}
