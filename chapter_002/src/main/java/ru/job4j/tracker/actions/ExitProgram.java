package ru.job4j.tracker.actions;

import ru.job4j.tracker.Input;
import ru.job4j.tracker.StartUI;
import ru.job4j.tracker.Tracker;
import ru.job4j.tracker.UserAction;

public class ExitProgram implements UserAction {
    int number;
    String description;
    StartUI ui;

    public ExitProgram(int number, String description, StartUI ui) {
        this.number = number;
        this.description = description;
        this.ui = ui;
    }

    @Override
    public int key() {
        return number;
    }

    @Override
    public void execute(Input input, Tracker tracker) {
        ui.stop();

    }

    @Override
    public String info() {
        return String.format("%s. %s", this.key(), this.description);
    }
}