package ru.job4j.tracker.actions;

import ru.job4j.tracker.Input;
import ru.job4j.tracker.Tracker;
import ru.job4j.tracker.UserAction;

public class DeleteItem implements UserAction {
    int number;
    String description;

    public DeleteItem(int number, String description) {
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
        if (tracker.delete(id)) {
            System.out.println("Заявка с ID " + id + " удалена");
        } else {
            System.out.println("Заявка с ID " + id + " не найдена");
        }
    }

    @Override
    public String info() {
        return String.format("%s. %s", this.key(), this.description);
    }
}

