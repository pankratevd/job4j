package ru.job4j.loop;

public class Mortgage {

    public int year(int amount, int monthly, double percent) {
        int year = 0;
        while (amount > 0) {
            year++;
            double amountWithPercent = amount * (1 + percent / 100);
            amount = (int) amountWithPercent - monthly * 12;
        }
        return year;
    }
}
