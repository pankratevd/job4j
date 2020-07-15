package isp;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class ActionMenuTest {


    @Test
    public void addMenuItem() {
        IBaseMenu menu = new SimpleMenu(new ConsoleInput(), new ConsoleOutput());

        IMenuItem item1 = new SimpleItem("Задача 1.", System.out::println);
        IMenuItem item11 = new SimpleItem("Задача 1.1.", item1, System.out::println);
        IMenuItem item2 = new SimpleItem("Задача 2.", System.out::println);

        menu.addMenuItem(item1);
        menu.addMenuItem(item2);
        menu.addMenuItem(item11);

        List<IMenuItem> expected = List.of(item1, item11, item2);
        List<IMenuItem> list = menu.getItems();

        assertThat(list, is(expected));

    }


    @Test
    public void getItem() {
        IBaseMenu menu = new SimpleMenu(new ConsoleInput(), new ConsoleOutput());

        IMenuItem item1 = new SimpleItem("Задача 1.", System.out::println);
        IMenuItem item11 = new SimpleItem("Задача 1.1.", item1, System.out::println);
        IMenuItem item2 = new SimpleItem("Задача 2.", System.out::println);

        menu.addMenuItem(item1);
        menu.addMenuItem(item2);
        menu.addMenuItem(item11);

        assertThat(menu.getItem("Задача 1."), is(item1));
        assertThat(menu.getItem("Задача 1.1."), is(item11));
        assertThat(menu.getItem("Задача 2."), is(item2));
        assertNull(menu.getItem("Задача 0."));
    }
}