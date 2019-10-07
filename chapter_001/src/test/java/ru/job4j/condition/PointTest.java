package ru.job4j.condition;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class PointTest {
    @Test
    public void whenZeroAndTenThenTen() {
        Point first = new Point(0, 0);
        Point second = new Point(0, 10);
        double result = first.distance(second);
        first.info();
        second.info();
        System.out.println(String.format("Result is %s", result));
        assertThat(result, is(10D));
    }

    @Test
    public void whenCheckItself() {
        Point point = new Point(0, 0);
        double result = point.distance(point);
        assertThat(result, is(0D));
    }

    @Test
    public void whenShowInfo() {
        Point first = new Point(1, 1);
        first.info();
        Point second = new Point(2, 2);
        second.info();
    }

    @Test
    public void distance3d1() {
        Point first = new Point(0, 0, 0);
        Point second = new Point(1, 1, 1);
        double result = first.distance3d(second);
        assertEquals(result, 1.73, 0.01);
    }

    @Test
    public void distance3d2() {
        Point first = new Point(0, 0, 0);
        Point second = new Point(0, 0, 0);
        double result = first.distance3d(second);
        assertEquals(result, 0, 0.01);
    }

    @Test
    public void distance3d3() {
        Point first = new Point(1, 2, 3);
        Point second = new Point(10, 20, 30);
        double result = first.distance3d(second);
        assertEquals(result, 33.67, 0.01);
    }
}

