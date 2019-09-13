package ru.job4j.tracker.actions;

import ru.job4j.tracker.*;

public class ShowItems implements UserAction {
    int number;
    String description;

    public ShowItems(int number, String description) {
        this.number = number;
        this.description = description;
    }

    @Override
    public int key() {
        return number;
    }

    @Override
    public void execute(Input input, Tracker tracker) {
        System.out.println("список всех заявок:");
        for (Item item : tracker.findAll()) {
            System.out.println("id: " + item.getId() + " имя: " + item.getName() + " описание: " + item.getDesc());
        }
    }

    @Override
    public String info() {
        return String.format("%s. %s", this.key(), this.description);
    }
}