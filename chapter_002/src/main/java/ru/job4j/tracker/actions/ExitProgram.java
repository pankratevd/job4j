package ru.job4j.tracker.actions;

import ru.job4j.tracker.Input;
import ru.job4j.tracker.StartUI;
import ru.job4j.tracker.Tracker;

import java.util.function.Consumer;

public class ExitProgram extends BaseAction {
    StartUI ui;

    public ExitProgram(int number, String description, StartUI ui, Consumer<String> output) {
        super(number, description, output);
        this.ui = ui;
    }


    @Override
    public void execute(Input input, Tracker tracker) {
        ui.stop();

    }

}