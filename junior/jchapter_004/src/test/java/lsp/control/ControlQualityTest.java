package lsp.control;


import lsp.food.Food;
import lsp.food.Meat;
import lsp.food.Milk;
import lsp.store.Shop;
import lsp.store.Trash;
import lsp.store.Warehouse;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ControlQualityTest {

    Shop shop = new Shop();
    Trash trash = new Trash();
    Warehouse warehouse = new Warehouse();

    Calendar milkCreated = Calendar.getInstance();
    Calendar milkExpired = Calendar.getInstance();
    Calendar meatCreated = Calendar.getInstance();
    Calendar meatExpired = Calendar.getInstance();

    @Test
    public void distributeWhenToShopWithoutMeatDiscount() {

        milkCreated.add(Calendar.DATE, -10);
        milkExpired.add(Calendar.DATE, 10);

        meatCreated.add(Calendar.DATE, -10);
        meatExpired.add(Calendar.DATE, 2);

        Food milk = new Milk("milk1", milkExpired, milkCreated, 100, 50);
        Food meat = new Meat("meat1", meatExpired, meatCreated, 500, 50);

        List<Food> list = new ArrayList<>();
        list.add(milk);
        list.add(meat);
        List<Food> expected = List.of(milk, meat);

        ControlQuality controlQuality = new ControlQuality(List.of(shop, warehouse, trash));
        controlQuality.distribute(list);

        assertThat(shop.getFood(), is(expected));
        assertThat(shop.getFood().get(0).getPrice(), is(100.0));
        assertThat(shop.getFood().get(1).getPrice(), is(250.0));
    }

    @Test
    public void whenMilkExpiredThenTrash() {
        milkCreated.add(Calendar.DATE, -10);
        milkExpired.add(Calendar.DATE, -1);

        meatCreated.add(Calendar.DATE, -10);
        meatExpired.add(Calendar.DATE, 8);

        Food milk = new Milk("milk1", milkExpired, meatCreated, 100, 50);
        Food meat = new Meat("meat1", meatExpired, meatCreated, 500, 50);

        List<Food> list = List.of(milk, meat);

        ControlQuality controlQuality = new ControlQuality(List.of(shop, warehouse, trash));
        controlQuality.distribute(list);
        assertThat(shop.getFood(), is(List.of(meat)));
        assertThat(trash.getFood(), is(List.of(milk)));
    }

    @Test
    public void distributeToWarehouse() {
        milkCreated.add(Calendar.DATE, -10);
        milkExpired.add(Calendar.DATE, 100);

        meatCreated.add(Calendar.DATE, -10);
        meatExpired.add(Calendar.DATE, 100);

        Food milk = new Milk("milk1", milkExpired, meatCreated, 100, 50);
        Food meat = new Meat("meat1", meatExpired, meatCreated, 500, 50);

        List<Food> list = List.of(milk, meat);

        ControlQuality controlQuality = new ControlQuality(List.of(shop, warehouse, trash));
        controlQuality.distribute(list);

        assertThat(warehouse.getFood(), is(List.of(milk, meat)));

    }
}