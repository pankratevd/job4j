package lsp.food;

import java.util.Calendar;

public class Milk extends Food {
    public Milk(String name, Calendar expireDate, Calendar createDate, double price, int discount) {
        super(name, expireDate, createDate, price, discount);
    }
}
