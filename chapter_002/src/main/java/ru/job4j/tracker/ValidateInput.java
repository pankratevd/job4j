package ru.job4j.tracker;

public class ValidateInput extends ConsoleInput {
    private int value = -1;
    private boolean invalid = true;

    @Override
    public int ask(String question, int[] range) {
        do {
            invalid = true;
            try {
                value = super.ask(question, range);
                invalid = false;

            } catch (NumberFormatException nfe) {
                System.out.println("Веедите коректные данные.");
            } catch (MenuOutException moe) {
                System.out.println("Введите значение из диапазона меню.");
            }
        } while (invalid);
        return value;
    }
}
