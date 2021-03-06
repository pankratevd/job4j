package ru.job4j.list;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ConvertList2ArrayTest {
    @Test
    public void when7ElementsThen9() {
        ConvertList2Array list = new ConvertList2Array();
        int[][] result = list.toArray(
                Arrays.asList(1, 2, 3, 4, 5, 6, 7),
                3
        );
        int[][] expect = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 0, 0}
        };
        assertThat(result, is(expect));
    }

    @Test
    public void when9ElementsThen9() {
        ConvertList2Array list = new ConvertList2Array();
        int[][] result = list.toArray(
                Arrays.asList(9, 8, 7, 6, 5, 4, 3, 2, 1),
                3
        );
        int[][] expect = {
                {9, 8, 7},
                {6, 5, 4},
                {3, 2, 1}
        };
        assertThat(result, is(expect));
    }

    @Test
    public void when10ElementsThen12() {
        ConvertList2Array list = new ConvertList2Array();
        int[][] result = list.toArray(
                Arrays.asList(9, 8, 7, 6, 5, 4, 3, 2, 1, 1),
                3
        );
        int[][] expect = {
                {9, 8, 7, 6},
                {5, 4, 3, 2},
                {1, 1, 0, 0}
        };
        assertThat(result, is(expect));
    }

    @Test
    public void when1ElementAnd3Rows() {
        ConvertList2Array list = new ConvertList2Array();
        int[][] result = list.toArray(
                Arrays.asList(1),
                3
        );
        int[][] expect = {{1}, {0}, {0}

        };
        assertThat(result, is(expect));
    }

    @Test
    public void checkConvert() {
        ConvertList2Array convertList = new ConvertList2Array();
        List<int[]> list = new ArrayList<>();
        list.add(new int[]{6, 5, 4});
        list.add(new int[]{3, 2});
        list.add(new int[]{1});
        List<Integer> result = convertList.convert(list);
        List<Integer> expected = Arrays.asList(6, 5, 4, 3, 2, 1);
        assertThat(result, is(expected));
    }

}