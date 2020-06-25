package lsp.store;

import lsp.food.Food;

public interface Store {

    boolean accept(Food food);
    void add(Food food);

}
