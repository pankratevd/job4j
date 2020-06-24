package lsp.control;

import lsp.food.Food;
import lsp.store.Trash;

public class MoveToTrash implements MoveStrategy {
    private Trash trash;
    /**
     * Minimal value of interval to use this Strategy (including)
     */
    private int min;

    /**
     * Maximal value of interval to use this Strategy (not including)
     **/
    private int max;

    public MoveToTrash(Trash trash, int min, int max) {
        this.trash = trash;
        this.min = min;
        this.max = max;
    }

    @Override
    public void distribute(Food food) {
        trash.addFood(food);
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
