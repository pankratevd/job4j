package ru.job4j.tracker;


public class StartUI {
    private static final String ADD = "0";
    private static final String FINDALL = "1";
    private static final String REPLACE = "2";
    private static final String DELETE = "3";
    private static final String FINDBYID = "4";
    private static final String FINDBYNAME = "5";
    private static final String EXIT = "6";
    private final Input input;
    private final Tracker tracker;

    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    public void init() {
        boolean exit = false;
        while (!exit) {
            this.showMenu();
            String answer = this.input.ask("Введите пункт меню : ");
            if (ADD.equals(answer)) {
                //добавление заявки вынесено в отдельный метод.
                this.createItem();

            } else if (FINDALL.equals(answer)) {
                System.out.println("список всех заявок:");
                this.printItems(this.tracker.findAll());

            } else if (REPLACE.equals(answer)) {
                this.replaceItem();

            } else if (DELETE.equals(answer)) {
                String id = this.input.ask("Введите ID:");
                if (this.tracker.delete(id)) {
                    System.out.println("Заявка с ID " + id + " удалена");
                } else {
                    System.out.println("Заявка с ID " + id + " не найдена");
                }

            } else if (FINDBYID.equals(answer)) {
                String id = this.input.ask("Введите ID:");
                Item item = this.tracker.findById(id);
                if (item != null) {
                    System.out.println("id: " + item.getId() + " name: " + item.getName() + " описание: " + item.getDesc());
                } else {
                    System.out.println("Заявка с ID " + id + " не найдена");
                }

            } else if (FINDBYNAME.equals(answer)) {
                String name = this.input.ask("Введите имя заявки:");
                if (this.tracker.findByName(name).length > 0) {
                    this.printItems(this.tracker.findByName(name));
                } else {
                    System.out.println("Заявки с именем " + name + " не найдены.");
                }
            } else if (EXIT.equals(answer)) {
                exit = true;
            }
        }

    }

    private void createItem() {
        System.out.println("------------ Добавление новой заявки --------------");
        String name = this.input.ask("Введите имя заявки :");
        String desc = this.input.ask("Введите описание заявки :");
        Item item = new Item(name, desc);
        this.tracker.add(item);
        System.out.println("------------ Новая заявка с getId : " + item.getId() + "-----------");
    }

    private void printItems(Item[] items) {
        for (Item item : items) {
            System.out.println("id: " + item.getId() + " name: " + item.getName() + " описание: " + item.getDesc());
        }
    }

    private void replaceItem() {
        String id = this.input.ask("Введите ID");
        Item item = this.tracker.findById(id);
        if (item != null) {
            String name = this.input.ask("Введите имя заявки :");
            String desc = this.input.ask("Введите описание заявки :");
            item.setName(name);
            item.setDesc(desc);
            this.tracker.replace(id, item);
        } else {
            System.out.println("Заявка с ID " + id + " не найдена");
        }
    }

    private void showMenu() {
        System.out.println("Меню.");
        System.out.println("0. Добавить новую заявку");
        System.out.println("1. Показать все заявки");
        System.out.println("2. Редактировать заявку");
        System.out.println("3. Удалить заявку");
        System.out.println("4. Найти по ID заявки");
        System.out.println("5. Найти по имени заявки");
        System.out.println("6. Выход");
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
