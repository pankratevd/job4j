package lsp.control;

import lsp.food.Food;
import lsp.store.Store;

import java.util.List;

/**
 * Class for distribute input List of Foods according to Strategy of distribution.
 */
public class ControlQuality {
    private final List<Store> stores;


    public ControlQuality(List<Store> stores) {
        this.stores = stores;
    }

    public void distribute(List<Food> foods) {
        for (Food f : foods) {
            moveToStore(f);
        }
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
