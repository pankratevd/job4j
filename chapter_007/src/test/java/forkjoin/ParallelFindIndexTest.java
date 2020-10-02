package forkjoin;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class ParallelFindIndexTest {
    @Test
    public void whenObjectExistThenIndex199() {

        Integer[] arr = new Integer[1000000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
        ForkJoinPool forkJoinPool = new ForkJoinPool(4);


        //for (int i = 0; i < 10000; i++) {
        int i = 999999;
        arr[23432] = 999999;
        long s = System.currentTimeMillis();
        ParallelFindIndex<Integer> array = new ParallelFindIndex<>(arr, i, 0, arr.length - 1);
        System.out.println("Start");
        forkJoinPool.invoke(array);
        //   Assert.assertThat(ParallelFindIndex.result, is(i));
        // }

        long e = System.currentTimeMillis();
        //System.out.println("Result: " + ParallelFindIndex.result);

        System.out.println((e - s));
        System.out.println(ParallelFindIndex.list);

        List<Integer> list = Arrays.asList(arr);
        s = System.currentTimeMillis();
        //   for (int i = 0; i < 10000; i++) {
    //    Assert.assertThat(list.indexOf(i), is(i));
        //   }
        System.out.println(list.indexOf(i));
        e = System.currentTimeMillis();

        System.out.println((e - s));
    }

    @Test
    public void whenNoObject() {
        Integer[] arr = new Integer[10000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.invoke(new ParallelFindIndex<>(arr, 999999999, 0, arr.length - 1));
        // Assert.assertThat(ParallelFindIndex.result, is(-1));

    }
}