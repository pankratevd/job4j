package ru.job4j.tracker.actions;

import ru.job4j.tracker.*;

public class ShowItems extends BaseAction {

    public ShowItems(int number, String description) {
        super(number, description);
    }

    @Override
    public void execute(Input input, Tracker tracker) {
        System.out.println("список всех заявок:");
        for (Item item : tracker.findAll()) {
            System.out.println("id: " + item.getId() + " имя: " + item.getName() + " описание: " + item.getDesc());
        }
    }
}