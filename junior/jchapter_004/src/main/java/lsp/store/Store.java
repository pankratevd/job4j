package lsp.store;

import lsp.food.Food;

import java.util.ArrayList;
import java.util.List;

public abstract class Store {
    protected String name;
    protected List<Food> list = new ArrayList<>();

    public Store(String name) {
        this.name = name;
    }

    public void addFood(Food food) {
        list.add(food);
    }

    public void removeFood(Food food) {
        list.remove(food);
    }

    public List<Food> getFood() {
        return list;
    }
}
