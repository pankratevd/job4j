package lsp.control;

import lsp.food.Food;

import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.List;

/**
 * Class for distribute input List of Foods according to Strategy of distribution.
 *
 */
public class ControlQuality {
    private Calendar currentDate = Calendar.getInstance();
    private final List<MoveStrategy> list;

    /**
     *
     * @param list - List of MoveStrategy.
     *             Params min and max in classes that implements interface should cover interval from 0 to 100.
     *             It is necessary includes Strategy with params min: 100 max: 100 to define Strategy for expired Foods.
     *             Example:
     *             List.of(new MoveToWarehouse(Store, min: 0, max: 25),new MoveToShop(Store, min: 25, max: 75), new MoveToShop(Store, min: 75, max: 100, discount: 50), new MoveToTrash(Store, min: 100, max: 100));
     *
     *
     */
    public ControlQuality(List<MoveStrategy> list) {
        this.list = list;
    }

    /**
     *
     * @param list
     * @param currentDate to set specific date as current.
     */
    public ControlQuality(List<MoveStrategy> list, Calendar currentDate) {
        this.list = list;
        this.currentDate = currentDate;
    }

    /**
     *
     * @param foods List of Food to distribute in according with Strategy from list.
     */
    public void distribute(List<Food> foods) {
        for (Food food : foods) {
            double shelfLife = 100 * (double) (ChronoUnit.DAYS.between(currentDate.toInstant(), food.getCreateDate().toInstant())) / (ChronoUnit.DAYS.between(food.getExpireDate().toInstant(), food.getCreateDate().toInstant()));
            strategy(shelfLife).executeStrategy(food);
        }
    }

    private MoveContext strategy(double shelfLife) {
        MoveContext context = null;
        for (MoveStrategy moveStrategy : this.list) {
            if ((shelfLife >= moveStrategy.getMin() && shelfLife < moveStrategy.getMax()) || (shelfLife >= 100.0 && moveStrategy.getMax() == 100 && moveStrategy.getMin() == 100.0)) {
                context = new MoveContext(moveStrategy);
                break;
            }
        }
        return context;
    }

}
