package ru.job4j.tracker;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class TrackerSingleEnumTest {

    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        TrackerSingleEnum tracker = TrackerSingleEnum.INSTANCE;
        TrackerSingleEnum tracker2 = TrackerSingleEnum.INSTANCE;
        Item item = new Item("test1");
        tracker.add(item);
        Item result = tracker2.findById(item.getId());
        assertThat(result.getName(), is(item.getName()));
    }

    @Test
    public void whenReplaceNameThenReturnNewName() {
        TrackerSingleEnum tracker = TrackerSingleEnum.INSTANCE;
        TrackerSingleEnum tracker2 = TrackerSingleEnum.INSTANCE;
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
        assertThat(tracker2.findById(previous.getId()).getName(), is("test2"));
    }

    @Test
    public void whenFindDeleteIsTrue() {
        TrackerSingleEnum tracker = TrackerSingleEnum.INSTANCE;
        TrackerSingleEnum tracker2 = TrackerSingleEnum.INSTANCE;
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
        assertThat(tracker2.delete(item3.getId()), is(true));
    }

    @Test
    public void whenFindDeleteIsFalse() {
        TrackerSingleEnum tracker = TrackerSingleEnum.INSTANCE;
        TrackerSingleEnum tracker2 = TrackerSingleEnum.INSTANCE;
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
        assertThat(tracker2.delete("wrongId"), is(false));
    }

    @Test
    public void whenFindAll() {
        TrackerSingleEnum tracker = TrackerSingleEnum.INSTANCE;
        TrackerSingleEnum tracker2 = TrackerSingleEnum.INSTANCE;
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
        Item[] expected = {item1, item2, item3, item4, item5};
        assertThat(tracker2.findAll(), is(expected));
    }

    @Test
    public void findByNameWhenExist() {
        TrackerSingleEnum tracker = TrackerSingleEnum.INSTANCE;
        TrackerSingleEnum tracker2 = TrackerSingleEnum.INSTANCE;
        Item item1 = new Item("task1");
        Item item2 = new Item("task2");
        Item item3 = new Item("task1");
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);
        Item[] expected = {item1, item3};
        assertThat(expected, is(tracker2.findByName("task1")));
    }

    @Test
    public void findByNameWhenNotExist() {
        TrackerSingleEnum tracker = TrackerSingleEnum.INSTANCE;
        TrackerSingleEnum tracker2 = TrackerSingleEnum.INSTANCE;
        Item item1 = new Item("task1");
        Item item2 = new Item("task2");
        Item item3 = new Item("task1");
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);
        Item[] expected = new Item[0];
        assertThat(expected, is(tracker2.findByName("task3")));
    }

    @Test
    public void findByIdWhenExist() {
        TrackerSingleEnum tracker = TrackerSingleEnum.INSTANCE;
        TrackerSingleEnum tracker2 = TrackerSingleEnum.INSTANCE;
        Item item1 = new Item("task1");
        Item item2 = new Item("task2");
        Item item3 = new Item("task1");
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);
        String id = item2.getId();
        assertThat(item2, is(tracker2.findById(id)));
    }

    @Test
    public void findBydWhenNotExist() {
        TrackerSingleEnum tracker = TrackerSingleEnum.INSTANCE;
        TrackerSingleEnum tracker2 = TrackerSingleEnum.INSTANCE;
        Item item1 = new Item("task1");
        Item item2 = new Item("task2");
        Item item3 = new Item("task1");
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);
        assertThat(null, is(tracker2.findById("wrongID")));
    }


    @Test
    public void chainAllOperations() {
        TrackerSingleEnum tracker = TrackerSingleEnum.INSTANCE;
        TrackerSingleEnum tracker2 = TrackerSingleEnum.INSTANCE;
        Item[] expected = new Item[0];
        assertThat(expected, is(tracker.findAll()));

        Item item1 = new Item("task1");
        Item item2 = new Item("task2");
        Item item2Replaced = new Item("task2Replaced");
        Item item3 = new Item("task3");
        tracker.add(item1);
        tracker.delete(item1.getId());
        assertThat(expected, is(tracker2.findAll()));

        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);
        tracker.replace(item2.getId(), item2Replaced);
        Item[] expected10 = {item1, item2Replaced, item3};
        assertThat(tracker2.findAll(), is(expected10));

        Item item4 = new Item("task4");
        tracker.add(item4);
        Item[] expected20 = {item1, item2Replaced, item3, item4};
        assertThat(expected20, is(tracker2.findAll()));

        tracker.delete(item3.getId());
        Item[] expected2 = {item1, item2Replaced, item4};
        assertThat(expected2, is(tracker2.findAll()));

        Item item1dublicate = new Item("task1");
        tracker.add(item1dublicate);

        Item[] expected30 = {item1, item1dublicate};
        assertThat(expected30, is(tracker2.findByName("task1")));

        Item item5 = new Item("task5");
        tracker.add(item5);
        Item[] expected40 = {item1, item2Replaced, item4, item1dublicate, item5};
        assertThat(expected40, is(tracker2.findAll()));

        tracker.delete(item5.getId());
        Item[] expected50 = {item1, item2Replaced, item4, item1dublicate};
        assertThat(expected50, is(tracker2.findAll()));

        Item item6 = new Item("task6");
        tracker.add(item6);
        Item[] expected60 = {item1, item2Replaced, item4, item1dublicate, item6};
        assertThat(expected60, is(tracker.findAll()));
    }


}
