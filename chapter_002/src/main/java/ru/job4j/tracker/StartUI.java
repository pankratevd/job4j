package ru.job4j.tracker;


import java.util.function.Consumer;

public class StartUI {

    private final Input input;
    private final Store store;
    private boolean working = true;
    private int[] range = new int[]{0, 1, 2, 3, 4, 5, 6};
    private final Consumer<String> output;

    public StartUI(Input input, Store store, Consumer<String> output) {
        this.input = input;
        this.store = store;
        this.output = output;
    }

    public void init() {
        MenuTracker menuTracker = new MenuTracker(input, store, this, this.output);
        menuTracker.fillActions();
        store.init();
        do {
            menuTracker.show();
            menuTracker.select(input.ask("Выберите меню:", range));
        }
        while (working);
    }

    public void stop() {
        this.working = false;
    }

    /**
     * Запускт программы.
     *
     * @param args
     */
    public static void main(String[] args) {
        new StartUI(new ValidateInput(new ConsoleInput()), new SqlTracker(), System.out::println).init();
    }

}
