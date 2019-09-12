package ru.job4j.tracker.actions;

import ru.job4j.tracker.Input;
import ru.job4j.tracker.MenuTracker;
import ru.job4j.tracker.Tracker;
import ru.job4j.tracker.UserAction;


public class FindItemsByName implements UserAction {
    int number;
    String description;
    MenuTracker tracker;

    public FindItemsByName(int number, String description, MenuTracker tracker) {
        this.number = number;
        this.description = description;
        this.tracker = tracker;
    }

    @Override
    public int key() {
        return number;
    }

    @Override
    public void execute(Input input, Tracker tracker) {
        String name = input.ask("Введите имя заявки:");
        if (tracker.findByName(name).length > 0) {
            this.tracker.printItems(tracker.findByName(name));
        } else {
            System.out.println("Заявки с именем " + name + " не найдены.");
        }
    }

    @Override
    public String info() {
        return String.format("%s. %s", this.key(), this.description);
    }
}
