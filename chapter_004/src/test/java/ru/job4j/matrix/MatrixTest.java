package ru.job4j.matrix;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class MatrixTest {

    @Test
    public void matrixToList() {
        Integer[][] matrix = {{1, 2}, {3, 4}, {5, 6}};
        List<Integer> expected = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6));
        List<Integer> result = new Matrix().matrixToList(matrix);
        assertThat(result, is(expected));
    }
}
