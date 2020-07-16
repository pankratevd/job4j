package lsp.store;

import lsp.food.Food;

import java.util.List;

public interface StoreExtended extends Store {

    boolean remove(Food food);

    List<Food> getFood();
}
