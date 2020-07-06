package lsp.parking;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class AutoTest {

    @Test
    public void testSize() {
        Auto car = new Auto() {
            int size = 1;
            String number = "А000АА77RUS";
            @Override
            public int size() {
                return this.size;
            }

            @Override
            public String number() {
                return this.number;
            }
        };

        int expectedSize = 1;
        assertThat(car.size(), is(expectedSize));

    }

    @Test
    public void testNumber() {
        Auto car = new Auto() {
            int size = 1;
            String number = "А000АА77RUS";
            @Override
            public int size() {
                return this.size;
            }

            @Override
            public String number() {
                return this.number;
            }
        };

        String expectedNumber = "А000АА77RUS";
        assertThat(car.number(), is(expectedNumber));
    }
}
