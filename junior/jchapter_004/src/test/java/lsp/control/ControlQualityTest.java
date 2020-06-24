package lsp.control;


import lsp.food.Food;
import lsp.food.Meat;
import lsp.food.Milk;
import lsp.store.Shop;
import lsp.store.Trash;
import lsp.store.Warehouse;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ControlQualityTest {

    Shop shop = new Shop("shop1");
    Trash trash = new Trash("trash1");
    Warehouse warehouse = new Warehouse("warehouse1");

    Calendar currentDate = Calendar.getInstance();
    Calendar milkCreated = Calendar.getInstance();
    Calendar milkExpired = Calendar.getInstance();
    Calendar meatCreated = Calendar.getInstance();
    Calendar meatExpired = Calendar.getInstance();
    List<MoveStrategy> listStrategy = new ArrayList<>();

    @Before
    public void init() {
        currentDate.set(2020, Calendar.AUGUST, 1);
        listStrategy.add(new MoveToWarehouse(warehouse, 0, 25));
        listStrategy.add(new MoveToShop(shop, 25, 75));
        listStrategy.add(new MoveToShop(shop, 75, 100, 50));
        listStrategy.add(new MoveToTrash(trash, 100, 100));
    }

    @Test
    public void distributeWhenToShopWithoutMeatDiscount() {
        milkCreated.set(2020, Calendar.JANUARY, 1);
        milkExpired.set(2021, Calendar.JANUARY, 1);

        meatCreated.set(2020, Calendar.JANUARY, 1);
        meatExpired.set(2020, Calendar.OCTOBER, 1);

        Food milk = new Milk("milk1", milkExpired, meatCreated, 100);
        Food meat = new Meat("meat1", meatExpired, meatCreated, 500);

        List<Food> list = new ArrayList<>();
        list.add(milk);
        list.add(meat);
        List<Food> expected = List.of(milk, meat);

        ControlQuality controlQuality = new ControlQuality(listStrategy, currentDate);
        controlQuality.distribute(list);

        assertThat(shop.getFood(), is(expected));
        assertThat(shop.getFood().get(0).getPrice(), is(100.0));
        assertThat(shop.getFood().get(1).getPrice(), is(250.0));
    }

    @Test
    public void whenMilkExpiredThenTrash() {
        milkCreated.set(2020, Calendar.JANUARY, 1);
        milkExpired.set(2020, Calendar.JULY, 31);

        meatCreated.set(2020, Calendar.JANUARY, 1);
        meatExpired.set(2020, Calendar.OCTOBER, 1);

        Food milk = new Milk("milk1", milkExpired, meatCreated, 100);
        Food meat = new Meat("meat1", meatExpired, meatCreated, 500);

        List<Food> list = List.of(milk, meat);

        ControlQuality controlQuality = new ControlQuality(listStrategy, currentDate);
        controlQuality.distribute(list);
        assertThat(shop.getFood(), is(List.of(meat)));
        assertThat(trash.getFood(), is(List.of(milk)));
    }

    @Test
    public void distributeToWarehouse() {
        milkCreated.set(2020, Calendar.JANUARY, 1);
        milkExpired.set(2023, Calendar.JULY, 31);

        meatCreated.set(2020, Calendar.JANUARY, 1);
        meatExpired.set(2023, Calendar.OCTOBER, 1);

        Food milk = new Milk("milk1", milkExpired, meatCreated, 100);
        Food meat = new Meat("meat1", meatExpired, meatCreated, 500);

        List<Food> list = List.of(milk, meat);

        ControlQuality controlQuality = new ControlQuality(listStrategy, currentDate);
        controlQuality.distribute(list);

        assertThat(warehouse.getFood(), is(List.of(milk, meat)));

    }

}