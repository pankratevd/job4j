package lsp.store;

import lsp.food.Food;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Trash implements Store {
    List<Food> list = new ArrayList<>();

    @Override
    public boolean accept(Food food) {
        return remainSelfLife(food) >= 100;
    }

    @Override
    public void add(Food food) {
        list.add(food);

    }

    public List<Food> getFood() {
        return list;
    }

    private double remainSelfLife(Food food) {
        Calendar currentDate = Calendar.getInstance();
        return 100 * (double) (ChronoUnit.DAYS.between(currentDate.toInstant(), food.getCreateDate().toInstant())) / (ChronoUnit.DAYS.between(food.getExpireDate().toInstant(), food.getCreateDate().toInstant()));
    }
}
