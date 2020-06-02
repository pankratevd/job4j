package ru.job4j.tracker.actions;

import ru.job4j.tracker.Input;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.MemTracker;
import ru.job4j.tracker.Store;

import java.util.function.Consumer;

public class AddItem extends BaseAction {

    public AddItem(int number, String description, Consumer<String> output) {
        super(number, description, output);
    }

    @Override
    public void execute(Input input, Store store) {
        this.output.accept("------------ Добавление новой заявки --------------");
        String name = input.ask("Введите имя заявки :");
        String desc = input.ask("Введите описание заявки :");
        Item item = new Item(name, desc);
        store.add(item);
        this.output.accept("------------ Новая заявка с getId : " + item.getId() + "-----------");

    }
}