package lsp.control;

import lsp.food.Food;
import lsp.store.Warehouse;

public class MoveToWarehouse implements MoveStrategy {
    private Warehouse warehouse;
    /**
     * Minimal value of interval to use this Strategy (including)
     */
    private int min;

    /**
     * Maximal value of interval to use this Strategy (not including)
     **/
    private int max;

    public MoveToWarehouse(Warehouse warehouse, int min, int max) {
        this.warehouse = warehouse;
        this.min = min;
        this.max = max;
    }

    @Override
    public void distribute(Food food) {
        this.warehouse.addFood(food);
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
