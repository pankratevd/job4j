package ru.job4j.converter;

import org.junit.Assert;
import org.junit.Test;

public class ConverterTest {

    @Test
    public void rubleToEuro() {
        int in = 140;
        int expected = 2;
        int out = Converter.rubleToEuro(in);
        Assert.assertEquals(expected, out);
    }

    @Test
    public void rubleToDollar() {
        int in2 = 180;
        int expected2 = 3;
        int out2 = Converter.rubleToDollar(in2);
        Assert.assertEquals(expected2, out2);
    }

    @Test
    public void euroToRuble() {
        int in3 = 2;
        int expected3 = 140;
        int out3 = Converter.euroToRuble(in3);
        Assert.assertEquals(expected3, out3);
    }

    @Test
    public void dollarToRuble() {
        int in4 = 3;
        int expected4 = 180;
        int out4 = Converter.dollarToRuble(in4);
        Assert.assertEquals(expected4, out4);
    }
}