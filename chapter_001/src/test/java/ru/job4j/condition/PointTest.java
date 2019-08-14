package ru.job4j.condition;

import org.junit.Assert;
import org.junit.Test;

public class PointTest {
    @Test
    public void distance() {
        int inX1 = 10;
        int inY1 = 0;
        int inX2 = 10;
        int inY2 = 6;
        int expected = 6;
        double out = Point.distance(inX1, inY1, inX2, inY2);
        Assert.assertEquals(expected, out, 0.0);

        inX1 = 0;
        inY1 = 0;
        inX2 = 0;
        inY2 = 0;
        expected = 0;
        out = Point.distance(inX1, inY1, inX2, inY2);
        Assert.assertEquals(expected, out, 0.0);
    }
}

