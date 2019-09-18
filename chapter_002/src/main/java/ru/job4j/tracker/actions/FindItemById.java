package ru.job4j.tracker.actions;

import ru.job4j.tracker.Input;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.Tracker;

public class FindItemById extends BaseAction {

    public FindItemById(int number, String description) {
        super(number, description);
    }


    @Override
    public void execute(Input input, Tracker tracker) {
        String id = input.ask("Введите ID:");
        Item item = tracker.findById(id);
        if (item != null) {
            System.out.println("id: " + item.getId() + " имя: " + item.getName() + " описание: " + item.getDesc());
        } else {
            System.out.println("Заявка с ID " + id + " не найдена");
        }
    }
}
