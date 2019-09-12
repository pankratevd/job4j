package ru.job4j.tracker.actions;

import ru.job4j.tracker.Input;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.Tracker;
import ru.job4j.tracker.UserAction;

public class FindItemById implements UserAction {
    int number;
    String description;

    public FindItemById(int number, String description) {
        this.number = number;
        this.description = description;
    }

    @Override
    public int key() {
        return number;
    }

    @Override
    public void execute(Input input, Tracker tracker) {
        String id = input.ask("Введите ID:");
        Item item = tracker.findById(id);
        if (item != null) {
            System.out.println("id: " + item.getId() + " name: " + item.getName() + " описание: " + item.getDesc());
        } else {
            System.out.println("Заявка с ID " + id + " не найдена");
        }
    }

    @Override
    public String info() {
        return String.format("%s. %s", this.key(), this.description);
    }
}
