package lsp.food;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public abstract class Food {

    private String name;
    private Calendar expireDate;
    private Calendar createDate;
    private double price;

    public Food(String name, Calendar expireDate, Calendar createDate, double price) {
        this.name = name;
        this.expireDate = expireDate;
        this.createDate = createDate;
        this.price = price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
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
