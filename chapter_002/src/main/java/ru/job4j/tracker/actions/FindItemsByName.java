package ru.job4j.tracker.actions;

import ru.job4j.tracker.*;


public class FindItemsByName implements UserAction {
    int number;
    String description;
    //MenuTracker tracker;

    public FindItemsByName(int number, String description) {
        this.number = number;
        this.description = description;
    }

    @Override
    public int key() {
        return number;
    }

    @Override
    public void execute(Input input, Tracker tracker) {
        String name = input.ask("Введите имя заявки:");
        for (Item item : tracker.findByName(name)) {
            System.out.println("id: " + item.getId() + " имя: " + item.getName() + " описание: " + item.getDesc());
        }
    }

    @Override
    public String info() {
        return String.format("%s. %s", this.key(), this.description);
    }
}
