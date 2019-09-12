package ru.job4j.tracker.actions;

import ru.job4j.tracker.Input;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.Tracker;
import ru.job4j.tracker.UserAction;

public class UpdateItem implements UserAction {
    int number;
    String description;

    public UpdateItem(int number, String description) {
        this.number = number;
        this.description = description;
    }

    @Override
    public int key() {
        return number;
    }

    @Override
    public void execute(Input input, Tracker tracker) {
        String id = input.ask("Введите ID");
        Item item = tracker.findById(id);
        if (item != null) {
            String name = input.ask("Введите имя заявки :");
            String desc = input.ask("Введите описание заявки :");
            item.setName(name);
            item.setDesc(desc);
            tracker.replace(id, item);
        } else {
            System.out.println("Заявка с ID " + id + " не найдена");
        }
    }

    @Override
    public String info() {
        return String.format("%s. %s", this.key(), this.description);
    }
}