package ru.job4j.tracker.actions;

import ru.job4j.tracker.Input;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.Tracker;

import java.util.function.Consumer;

public class UpdateItem extends BaseAction {

    public UpdateItem(int number, String description, Consumer<String> output) {
        super(number, description, output);
    }

    @Override
    public void execute(Input input, Tracker tracker) {
        String id = input.ask("Введите ID");
        String name = input.ask("Введите имя заявки :");
        String desc = input.ask("Введите описание заявки :");
        if (tracker.replace(id, new Item(name, desc))) {
            this.output.accept(String.format("Заявка с ID %s обновлена%n", id));
        } else {
            this.output.accept(String.format("Заявка с ID %s не найдена%n", id));
        }

    }
}