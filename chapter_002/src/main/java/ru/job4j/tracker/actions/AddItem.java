package ru.job4j.tracker.actions;

import ru.job4j.tracker.Input;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.Tracker;

import java.util.function.Consumer;

public class AddItem extends BaseAction {

    public AddItem(int number, String description, Consumer<String> output) {
        super(number, description, output);
    }

    @Override
    public void execute(Input input, Tracker tracker) {
        this.output.accept("------------ Добавление новой заявки --------------");
        String name = input.ask("Введите имя заявки :");
        String desc = input.ask("Введите описание заявки :");
        Item item = new Item(name, desc);
        tracker.add(item);
        this.output.accept("------------ Новая заявка с getId : " + item.getId() + "-----------");

    }
}