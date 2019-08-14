package ru.job4j.calculator;

import org.junit.Assert;
import org.junit.Test;

public class FitTest {
    @Test
    public void manWeight() {
        int in1 = 170;
        double expected1 = 80.5;
        double out1 = Fit.manWeight(in1);
        Assert.assertEquals(expected1, out1, 0.0);
    }

    @Test
    public void womanWeight() {
        int in2 = 170;
        double expexted2 = 69;
        double out2 = Fit.womanWeight(170);
        Assert.assertEquals(expexted2, out2, 0.0);
    }

}
