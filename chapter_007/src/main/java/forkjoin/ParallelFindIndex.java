package forkjoin;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveAction;

public class ParallelFindIndex<T> extends RecursiveAction {

    private final T[] arr;
    private final T obj;
    private final int start;
    private final int end;
    static int result = -1;
    static List<Integer> list = new ArrayList<>();

    public ParallelFindIndex(T[] arr, T obj, int start, int end) {
        this.arr = arr;
        this.obj = obj;
        this.start = start;
        this.end = end;
    }

    @Override
    protected void compute() {
        //  if (result != -1) return;
        if (end - start < 100) {
            process(start, end);
        } else {

            int mid = (start + end) / 2;
            ParallelFindIndex<T> p1 = new ParallelFindIndex<T>(arr, obj, start, mid);
            ParallelFindIndex<T> p2 = new ParallelFindIndex<>(arr, obj, mid + 1, end);
            p1.fork();
            p2.fork();
            p1.join();
            p2.join();
        }
    }

    private int process(int start, int end) {
        for (int i = start; i <= end; i++) {
            //      System.out.printf("Search from %s to %s \n", start, end);
            if (obj.equals(arr[i])) {
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!! FOUND !!!!!!!!!!!!!!!!!!!!");
                //result = i;
                list.add(i);

            }
        }
        return result;
    }
}
