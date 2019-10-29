package ru.job4j.tracker.actions;

import ru.job4j.tracker.*;

import java.util.function.Consumer;

public class ShowItems extends BaseAction {

    public ShowItems(int number, String description, Consumer<String> output) {
        super(number, description, output);
    }

    @Override
    public void execute(Input input, Tracker tracker) {
        this.output.accept("список всех заявок:");
        for (Item item : tracker.findAll()) {
            this.output.accept("id: " + item.getId() + " имя: " + item.getName() + " описание: " + item.getDesc());
        }
    }
}