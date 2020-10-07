package forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class ParallelFindIndex<T> {
    private ForkJoinPool forkJoinPool;
    int result;

    public ParallelFindIndex() {
        this.forkJoinPool = ForkJoinPool.commonPool();
    }

    public ParallelFindIndex(int number) {
        this.forkJoinPool = new ForkJoinPool(number);
    }

    public int getIndex(T[] arr, T obj) {
        result = -1;
        ParallelFindIndexInner array = new ParallelFindIndexInner(arr, obj, 0, arr.length - 1);
        forkJoinPool.invoke(array);
        return result;
    }

    class ParallelFindIndexInner extends RecursiveAction {

        private final T[] arr;
        private final T obj;
        private final int start;
        private final int end;

        private ParallelFindIndexInner(T[] arr, T obj, int start, int end) {
            this.arr = arr;
            this.obj = obj;
            this.start = start;
            this.end = end;
        }

        @Override
        protected void compute() {
            if ((end - start) < 10) {
                process(start, end);
            } else {
                int mid = (start + end) / 2;
                ParallelFindIndexInner p1 = new ParallelFindIndexInner(arr, obj, start, mid);
                ParallelFindIndexInner p2 = new ParallelFindIndexInner(arr, obj, mid + 1, end);
                p1.fork();
                p2.fork();
                p1.join();
                p2.join();
            }
        }

        private void process(int start, int end) {
            for (int i = start; i <= end; i++) {
                if (obj.equals(arr[i])) {
                    result = i;
                }
            }
        }
    }
}
