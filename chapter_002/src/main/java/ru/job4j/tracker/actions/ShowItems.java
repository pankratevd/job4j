package ru.job4j.tracker.actions;

import ru.job4j.tracker.Input;
import ru.job4j.tracker.MenuTracker;
import ru.job4j.tracker.Tracker;
import ru.job4j.tracker.UserAction;

public class ShowItems implements UserAction {
    int number;
    String description;
    private MenuTracker tracker;

    public ShowItems(int number, String description, MenuTracker tracker) {
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
        System.out.println("список всех заявок:");
        this.tracker.printItems(tracker.findAll());
    }

    @Override
    public String info() {
        return String.format("%s. %s", this.key(), this.description);
    }
}