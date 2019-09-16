package ru.job4j.tracker.actions;

import ru.job4j.tracker.*;


public class FindItemsByName extends BaseAction {

    public FindItemsByName(int number, String description) {
        super(number, description);
    }

    @Override
    public void execute(Input input, Tracker tracker) {
        String name = input.ask("Введите имя заявки:");
        for (Item item : tracker.findByName(name)) {
            System.out.println("id: " + item.getId() + " имя: " + item.getName() + " описание: " + item.getDesc());
        }
    }
}
