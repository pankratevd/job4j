package ru.job4j.tracker;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


public class StartUITest {
    private String ls = System.lineSeparator();
    private final PrintStream stdout = System.out;
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    private String menu() {
        return new StringBuilder()
                .append("Меню.")
                .append(ls)
                .append("0. Добавить новую заявку")
                .append(ls)
                .append("1. Показать все заявки")
                .append(ls)
                .append("2. Редактировать заявку")
                .append(ls)
                .append("3. Удалить заявку")
                .append(ls)
                .append("4. Найти по ID заявки")
                .append(ls)
                .append("5. Найти по имени заявки")
                .append(ls)
                .append("6. Выход")
                .append(ls)
                .toString();
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
    public void whenShowAllItems() {
        Tracker tracker = new Tracker();
        Item item1 = new Item("task 1", "desc 1");
        tracker.add(item1);
        Input input = new StubInput(new String[]{"1", "6"});
        new StartUI(input, tracker).init();
        assertThat(out.toString(), is(
                new StringBuilder()
                        .append(menu())
                        .append("список всех заявок:")
                        .append(ls)
                        .append("id: ")
                        .append(item1.getId())
                        .append(" имя: task 1 описание: desc 1")
                        .append(ls)
                        .append(menu()).toString()));
    }

    @Test
    public void whenFindByID() {
        Tracker tracker = new Tracker();
        Item item1 = new Item("task 1", "desc 1");
        Item item2 = new Item("task 2", "desc 2");
        Item item3 = new Item("task 3", "desc 3");
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);
        Input input = new StubInput(new String[]{"4", item2.getId(), "6"});
        new StartUI(input, tracker).init();
        assertThat(out.toString(), is(
                new StringBuilder()
                        .append(menu())
                        .append("id: ")
                        .append(item2.getId())
                        .append(" имя: task 2 описание: desc 2")
                        .append(ls)
                        .append(menu()).toString())
        );
    }

    @Test
    public void whenFindByName() {
        Tracker tracker = new Tracker();
        Item item1 = new Item("task 1", "desc 1");
        Item item2 = new Item("task 1", "desc 2");
        Item item3 = new Item("task 3", "desc 3");
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);
        Input input = new StubInput(new String[]{"5", item2.getName(), "6"});
        new StartUI(input, tracker).init();
        assertThat(out.toString(), is(
                new StringBuilder()
                        .append(menu())
                        .append("id: ")
                        .append(item1.getId())
                        .append(" имя: task 1 описание: desc 1")
                        .append(ls)
                        .append("id: ")
                        .append(item2.getId())
                        .append(" имя: task 1 описание: desc 2")
                        .append(ls)
                        .append(menu()).toString())
        );
    }
}

