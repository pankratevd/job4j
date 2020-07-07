package lsp.parking;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class CarTest {

    @Test
    public void testSize() {
        Auto car = new Car("A001AA77RUS", 1);
        int expectedSize = 1;
        assertThat(car.size(), is(expectedSize));

    }

    @Test
    public void testNumber() {
        Auto car = new Car("А001АА77RUS", 1);

        String expectedNumber = "А001АА77RUS";
        assertThat(car.number(), is(expectedNumber));
    }
}
