package isp;

import java.util.Optional;

public class Menu {
    public static void main(String[] args) {
        IBaseMenu menu = new SimpleMenu(new ConsoleInput(), new ConsoleOutput());

        IMenuItem item1 = new SimpleItem("Задача 1.", () -> {
            System.out.println("Действие для Задачи 1.");
        });
        IMenuItem item11 = new SimpleItem("Задача 1.1.", item1, () -> {
            System.out.println("Действие для Задачи 1.1.");
        });
        IMenuItem item111 = new SimpleItem("Задача 1.1.1.", item11, () -> {
            System.out.println("Действие для Задачи 1.1.1.");
        });
        IMenuItem item112 = new SimpleItem("Задача 1.1.2.", item11, () -> {
            System.out.println("Действие для Задачи 1.1.2");
        });
        IMenuItem item2 = new SimpleItem("Задача 2.", () -> {
            System.out.println("Действие для Задачи 2.");
        });
        IMenuItem item1121 = new SimpleItem("Задача 1.1.2.1.", item112, () -> {
            System.out.println("Действие для Задачи 1.1.2.1.");
        });
        IMenuItem item0 = new SimpleItem("0. Выход", () -> System.exit(0));

        menu.addMenuItem(item0);
        menu.addMenuItem(item1);
        menu.addMenuItem(item2);
        menu.addMenuItem(item11);
        menu.addMenuItem(item112);
        menu.addMenuItem(item111);
        menu.addMenuItem(item1121);

        String in = "";
        while (!item0.getTitle().equals(in)) {
            menu.printMenu();
            in = menu.input();
            Optional<IMenuItem> item = Optional.ofNullable(menu.getItem(in));
            item.ifPresent(IMenuItem::action);
        }
    }
}
