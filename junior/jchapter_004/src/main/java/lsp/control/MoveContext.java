package lsp.control;

import lsp.food.Food;

public class MoveContext {
    MoveStrategy strategy;

    public MoveContext(MoveStrategy strategy) {
        this.strategy = strategy;
    }

    public void executeStrategy(Food food) {
        strategy.distribute(food);
    }
}
