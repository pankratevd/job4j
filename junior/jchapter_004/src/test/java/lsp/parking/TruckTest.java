package lsp.parking;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class TruckTest {

    @Test
    public void testSize() {
        Auto van = new Truck("A001AA77RUS", 1);
        int expectedSize = 1;
        assertThat(van.size(), is(expectedSize));

    }

    @Test
    public void testNumber() {
        Auto van = new Truck("А001АА77RUS", 1);

        String expectedNumber = "А001АА77RUS";
        assertThat(van.number(), is(expectedNumber));
    }
}
