package ru.job4j.tracker;


public class StartUI {

    private final Input input;
    private final Tracker tracker;
    private boolean working = true;

    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    public void init() {
        MenuTracker menuTracker = new MenuTracker(input, tracker, this);
        menuTracker.fillActions();
        do {
            menuTracker.show();
            int k = Integer.valueOf(input.ask("Выберите меню:"));
            menuTracker.select(k);
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
        new StartUI(new ConsoleInput(), new Tracker()).init();
    }

}
