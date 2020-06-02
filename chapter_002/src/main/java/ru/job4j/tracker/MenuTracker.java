package ru.job4j.tracker;

import ru.job4j.tracker.actions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;


public class MenuTracker {
    /**
     * @param хранит ссылку на объект .
     */
    private Input input;
    /**
     * @param хранит ссылку на объект .
     */
    private Store store;

    private StartUI ui;
    /**
     * @param хранит ссылку на массив типа UserAction.
     */
    private List<UserAction> actions = new ArrayList<>();

    private final Consumer<String> output;

    /**
     * Конструктор.
     *
     * @param input  объект типа Input
     * @param store  объект типа Tracker
     * @param output
     */
    public MenuTracker(Input input, Store store, StartUI ui, Consumer<String> output) {
        this.input = input;
        this.store = store;
        this.ui = ui;
        this.output = output;
    }

    /**
     * Метод заполняет массив.
     */
    public void fillActions() {
        this.actions.add(new AddItem(0, "Добавить новую заявку", output));
        this.actions.add(new ShowItems(1, "Показать все заявки", output));
        this.actions.add(new UpdateItem(2, "Редактировать заявку", output));
        this.actions.add(new DeleteItem(3, "Удалить заявку", output));
        this.actions.add(new FindItemById(4, "Найти по ID заявки", output));
        this.actions.add(new FindItemsByName(5, "Найти по имени заявки", output));
        this.actions.add(new ExitProgram(6, "Выход", this.ui, output));

    }

    /**
     * Метод в зависимости от указанного ключа, выполняет соотвествующие действие.
     *
     * @param key ключ операции
     */
    public void select(int key) {
        this.actions.get(key).execute(this.input, this.store);
    }

    /**
     * Метод выводит на экран меню.
     */
    public void show() {
        output.accept("Меню.");
        for (UserAction action : this.actions) {
            if (action != null) {
                this.output.accept(action.info());
            }
        }
    }

    public void printItems(ArrayList<Item> items) {
        for (Item item : items) {
            System.out.println("id: " + item.getId() + " имя: " + item.getName() + " описание: " + item.getDesc());
        }
    }


}
