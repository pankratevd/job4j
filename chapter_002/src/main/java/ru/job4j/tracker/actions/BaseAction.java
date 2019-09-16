package ru.job4j.tracker.actions;

import ru.job4j.tracker.UserAction;

public abstract class BaseAction implements UserAction {
    private final int number;
    private final String description;

    public BaseAction(final int number, final String description) {
        this.number = number;
        this.description = description;
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
