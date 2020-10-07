package forkjoin;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ParallelFindIndexTest {
    @Test
    public void whenObjectExists() {
        int count = 10000;
        Integer[] arr = new Integer[count];
        for (int i = 0; i < count; i++) {
            arr[i] = i;
        }

        ParallelFindIndex<Integer> parallelFindIndex = new ParallelFindIndex<>();
        for (int i = 0; i < count; i++) {
            assertThat(parallelFindIndex.getIndex(arr, i), is(i));
        }
    }

    @Test
    public void whenNoObjectExists() {
        int count = 10000;
        Integer[] arr = new Integer[count];
        for (int i = 0; i < count; i++) {
            arr[i] = i;
        }

        ParallelFindIndex<Integer> parallelFindIndex = new ParallelFindIndex<>();
        for (int i = 0; i < count; i++) {
            assertThat(parallelFindIndex.getIndex(arr, count + i), is(-1));
        }
    }
}