package ru.job4j.tracker.actions;

import ru.job4j.tracker.Input;
import ru.job4j.tracker.Tracker;

public class DeleteItem extends BaseAction {

    public DeleteItem(int number, String description) {
        super(number, description);
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
}

