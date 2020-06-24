package lsp.control;

import lsp.food.Food;
import lsp.store.Shop;
import lsp.store.Store;

public class MoveToShop implements MoveStrategy {
    private Store shop;
    private int discount;
    /**
     * Minimal value of interval to use this Strategy (including)
     */
    private int min;

    /**
    * Maximal value of interval to use this Strategy (not including)
    **/
    private int max;

    public MoveToShop(Shop shop, int min, int max, int discount) {
        this.shop = shop;
        this.min = min;
        this.max = max;
        this.discount = discount;
    }

    public MoveToShop(Shop shop, int min, int max) {
        this.shop = shop;
        this.min = min;
        this.max = max;
    }


    @Override
    public void distribute(Food food) {
        this.shop.addFood(food);
        if (discount != 0) {
            food.setPrice(food.getPrice() * (100 - this.discount) / 100);
        }
    }

    @Override
    public double getMin() {
        return this.min;
    }

    @Override
    public double getMax() {
        return this.max;
    }
}
