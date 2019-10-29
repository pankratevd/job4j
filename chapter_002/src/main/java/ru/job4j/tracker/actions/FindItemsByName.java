package ru.job4j.tracker.actions;

import ru.job4j.tracker.*;

import java.util.function.Consumer;


public class FindItemsByName extends BaseAction {

    public FindItemsByName(int number, String description, Consumer<String> output) {
        super(number, description, output);
    }

    @Override
    public void execute(Input input, Tracker tracker) {
        String name = input.ask("Введите имя заявки:");
        for (Item item : tracker.findByName(name)) {
            this.output.accept("id: " + item.getId() + " имя: " + item.getName() + " описание: " + item.getDesc());
        }
    }
}
