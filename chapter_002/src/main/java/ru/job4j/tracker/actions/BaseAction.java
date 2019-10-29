package ru.job4j.tracker.actions;

import ru.job4j.tracker.UserAction;

import java.util.function.Consumer;

public abstract class BaseAction implements UserAction {
    private final int number;
    private final String description;
    final Consumer<String> output;

    public BaseAction(int number, String description, Consumer<String> output) {
        this.number = number;
        this.description = description;
        this.output = output;
    }

    @Override
    public int key() {
        return number;
    }

    @Override
    public String info() {
        return String.format("%s. %s", this.key(), this.description);
    }
}
