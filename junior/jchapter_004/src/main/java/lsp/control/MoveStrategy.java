package lsp.control;

import lsp.food.Food;

public interface MoveStrategy {

    public void distribute(Food food);
    public double getMin();
    public double getMax();
}
