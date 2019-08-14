package ru.job4j.condition;

import org.junit.Assert;
import org.junit.Test;

public class SqAreaTest {
    @Test
    public void square() {
        int inP = 4;
        int inK = 1;
        int expected = 1;
        int out = SqArea.square(inP, inK);
        Assert.assertEquals(expected, out);

        inP = 6;
        inK = 2;
        expected = 2;
        out = SqArea.square(inP, inK);
        Assert.assertEquals(expected, out);
    }
}
