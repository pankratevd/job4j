package lsp.food;

import java.util.Calendar;

public class Meat extends Food {
    public Meat(String name, Calendar expireDate, Calendar createDate, double price, int discount) {
        super(name, expireDate, createDate, price, discount);
    }
}
