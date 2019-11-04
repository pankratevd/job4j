package ru.job4j.function;


import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertArrayEquals;

public class FunctionalTest {

    @Test
    public void whenLinearFunctionThenLinearResults() {
        Functional function = new Functional();
        List<Double> result = function.diapason(5, 8, x -> 2 * x + 1);
        List<Double> expected = Arrays.asList(11D, 13D, 15D);
        assertThat(result, is(expected));
    }

    @Test
    public void whenLinearFunctionThenQuadraticResults() {
        Functional function = new Functional();
        List<Double> result = function.diapason(5, 8, x -> x * x);
        List<Double> expected = Arrays.asList(25D, 36D, 49D);
        assertThat(result, is(expected));
    }

    @Test
    public void whenLinearFunctionThenLogarithmicResults() {
        Functional function = new Functional();
        List<Double> resultList = function.diapason(5, 8, x -> Math.log(x));
        double[] result = new double[resultList.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = resultList.get(i);
        }
        double[] expected = {1.609D, 1.792D, 1.946D};
        assertArrayEquals(result, expected, 0.001);

    }

}