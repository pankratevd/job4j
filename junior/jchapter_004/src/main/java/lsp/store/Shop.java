package lsp.store;

import lsp.food.Food;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Shop implements StoreExtended {
    List<Food> list = new ArrayList<>();

    @Override
    public boolean accept(Food food) {
        double selfLife = remainSelfLife(food);
        return selfLife >= 25 && selfLife < 100;
    }

    @Override
    public void add(Food food) {
        if (remainSelfLife(food) > 75) {
            food.setPrice(food.getPrice() * food.getDiscount() / 100);
        }
        list.add(food);
    }

    @Override
    public List<Food> getFood() {
        return list;
    }

    @Override
    public boolean remove(Food food) {
        return this.list.remove(food);
    }

    private double remainSelfLife(Food food) {
        Calendar currentDate = Calendar.getInstance();
        return 100 * (double) (ChronoUnit.DAYS.between(currentDate.toInstant(), food.getCreateDate().toInstant())) / (ChronoUnit.DAYS.between(food.getExpireDate().toInstant(), food.getCreateDate().toInstant()));
    }
}
