package ru.job4j.tracker;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


public class StartUITest {
    String ls = System.lineSeparator();
    private final PrintStream stdout = System.out;
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final String menu = "Меню.\n0. Добавить новую задачу\n1. Показать все заявки\n2. Редактировать заявку\n3. Удалить заявку\n4. Найти по ID заявки\n5. Найти по имени заявки\n6. Выход\n";


    private void printItems(Item[] items) {
        for (Item item : items) {
            System.out.println("id: " + item.getId() + " name: " + item.getName() + " описание: " + item.getDesc());
        }
    }

    @Before
    public void loadOutput() {
        System.setOut(new PrintStream(out));
    }

    @After
    public void backOutput() {
        System.setOut(stdout);
    }


    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
        Tracker tracker = new Tracker();
        Input input = new StubInput(new String[]{"0", "test name", "desc", "6"});   //создаём StubInput с последовательностью действий
        new StartUI(input, tracker).init();     //   создаём StartUI и вызываем метод init()
        assertThat(tracker.findAll()[0].getName(), is("test name")); // проверяем, что нулевой элемент массива в трекере содержит имя, введённое при эмуляции.
    }

    @Test
    public void whenUpdateThenTrackerHasUpdatedValue() {
        // создаём Tracker
        Tracker tracker = new Tracker();
        //Напрямую добавляем заявку
        Item item = tracker.add(new Item("test name", "desc"));
        //создаём StubInput с последовательностью действий(производим замену заявки)
        Input input = new StubInput(new String[]{"2", item.getId(), "test replace", "заменили заявку", "6"});
        // создаём StartUI и вызываем метод init()
        new StartUI(input, tracker).init();
        // проверяем, что нулевой элемент массива в трекере содержит имя, введённое при эмуляции.
        assertThat(tracker.findById(item.getId()).getName(), is("test replace"));
    }

    @Test
    public void whenDeleteFirstTaskTrackerHasOnlySecond() {
        Tracker tracker = new Tracker();
        Item item1 = tracker.add(new Item("task 1", "desc 1"));
        tracker.add(new Item("task 2", "desc 2"));
        assertThat(tracker.findAll().length, is(2));
        Input input = new StubInput(new String[]{"3", item1.getId(), "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll().length, is(1));
        assertThat(tracker.findAll()[0].getName(), is("task 2"));
    }

    @Test
    public void showAllItems() {
        Tracker tracker = new Tracker();
        Item item1 = new Item("task 1", "desc 1");
        tracker.add(item1);
        Input input = new StubInput(new String[]{"1", "6"});
        new StartUI(input, tracker).init();
        assertThat(out, is(
                new StringBuilder()
                        .append(menu)
                        .append("список всех заявок:\n")
                        .append("id: ")
                        .append(item1.getId())
                        .append(" name: task 1 описание: desc 1\n")
                        .append(menu)));
    }

}
