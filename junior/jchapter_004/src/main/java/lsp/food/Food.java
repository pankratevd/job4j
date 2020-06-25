package lsp.food;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public abstract class Food {

    private String name;
    private Calendar expireDate;
    private Calendar createDate;
    private double price;
    private int discount;

    public Food(String name, Calendar expireDate, Calendar createDate, double price, int discount) {
        this.name = name;
        this.expireDate = expireDate;
        this.createDate = createDate;
        this.price = price;
        this.discount = discount;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public int getDiscount() {
        return discount;
    }


    public Calendar getExpireDate() {
        return expireDate;
    }

    public Calendar getCreateDate() {
        return createDate;
    }

    @Override
    public String toString() {

        return "Food{"
                + "name='" + name + '\''
                + ", expireDate=" + new SimpleDateFormat("yyyy-MM-dd").format(expireDate.getTime())
                + ", createDate=" + new SimpleDateFormat("yyyy-MM-dd").format(createDate.getTime())
                + ", price=" + price
                + '}';
    }
}
