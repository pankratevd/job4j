package ru.job4j.tracker;

public class ValidateInput implements Input {
    private final Input input;

    public ValidateInput(final Input input) {
        this.input = input;
    }

    @Override
    public String ask(String question) {
        return this.input.ask(question);
    }

    @Override
    public int ask(String question, int[] range) {
        int value = -1;
        boolean invalid = true;
        do {
            invalid = true;
            try {
                value = this.input.ask(question, range);
                invalid = false;

            } catch (NumberFormatException nfe) {
                System.out.println("Введите коректные данные.");
            } catch (MenuOutException moe) {
                System.out.println("Введите значение из диапазона меню.");
            }
        } while (invalid);
        return value;
    }
}
