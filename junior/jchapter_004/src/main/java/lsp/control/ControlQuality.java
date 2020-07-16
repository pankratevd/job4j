package lsp.control;

import lsp.food.Food;
import lsp.store.Store;
import lsp.store.StoreExtended;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for distribute input List of Foods according to Strategy of distribution.
 */
public class ControlQuality {
    private final List<StoreExtended> stores;


    public ControlQuality(List<StoreExtended> stores) {
        this.stores = stores;
    }

    public void distribute(List<Food> foods) {
        for (Food f : foods) {
            moveToStore(f);
        }
    }

    public void resort() {
        List<Food> foods = new ArrayList<>();
        for (StoreExtended store : stores) {
            List<Food> getFoods = new ArrayList<>();
            getFoods.addAll(store.getFood());
            foods.addAll(getFoods);
            for (Food f : getFoods) {
                store.remove(f);
            }
        }
        this.distribute(foods);
    }

    private void moveToStore(Food food) {
        for (Store s : stores) {
            if (s.accept(food)) {
                s.add(food);
                break;
            }
        }
    }
}
