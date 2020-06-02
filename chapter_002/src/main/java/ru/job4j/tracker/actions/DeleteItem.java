package ru.job4j.tracker.actions;

import ru.job4j.tracker.Input;
import ru.job4j.tracker.MemTracker;
import ru.job4j.tracker.Store;

import java.util.function.Consumer;

public class DeleteItem extends BaseAction {

    public DeleteItem(int number, String description, Consumer<String> output) {
        super(number, description, output);
    }

    @Override
    public void execute(Input input, Store store) {
        String id = input.ask("Введите ID:");
        if (store.delete(id)) {
            this.output.accept("Заявка с ID " + id + " удалена");
        } else {
            this.output.accept("Заявка с ID " + id + " не найдена");
        }
    }
}

