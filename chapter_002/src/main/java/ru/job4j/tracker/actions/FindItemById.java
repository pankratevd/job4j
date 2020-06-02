package ru.job4j.tracker.actions;

import ru.job4j.tracker.Input;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.MemTracker;
import ru.job4j.tracker.Store;

import java.util.function.Consumer;

public class FindItemById extends BaseAction {

    public FindItemById(int number, String description, Consumer<String> output) {
        super(number, description, output);
    }


    @Override
    public void execute(Input input, Store store) {
        String id = input.ask("Введите ID:");
        Item item = store.findById(id);
        if (item != null) {
            this.output.accept("id: " + item.getId() + " имя: " + item.getName() + " описание: " + item.getDesc());
        } else {
            this.output.accept("Заявка с ID " + id + " не найдена");
        }
    }
}
