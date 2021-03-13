package ru.job4j.others;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelFindIndex extends RecursiveTask<Integer> {

    private final Object[] array;
    private final Object object;
    private final int from;
    private final int to;

    public ParallelFindIndex(Object[] array, Object object, int from, int to) {
        this.array = array;
        this.object = object;
        this.from = from;
        this.to = to;
    }

    private static Integer findIndex(Object[] array, Object object, int from, int to) {
        int index = -1;
        for (int i = from; i <= to; i++) {
            if (array[i].equals(object)) {
                index = i;
                break;
            }
        }
        return index;
    }

    @Override
    protected Integer compute() {
        if (to - from <= 10) {
            return findIndex(array, object, from, to);
        }
        int middle = (to - from) / 2;

        ParallelFindIndex leftFind = new ParallelFindIndex(array, object, from, from + middle);
        ParallelFindIndex rightFind = new ParallelFindIndex(array, object, from + middle + 1, to);

        leftFind.fork();
        rightFind.fork();

        int leftInt = leftFind.join();
        int rightInt = rightFind.join();

        if (leftInt != -1) {
            return leftInt;
        } else {
            return rightInt;
        }
    }

    public static Integer parallelFindIndex(Object[] array, Object object) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool.invoke(new ParallelFindIndex(array, object, 0, array.length - 1));
    }

    public static void main(String[] args) {
        Integer[] integers = new Integer[456];
        for (int i = 0; i < integers.length; i++) {
            integers[i] = new Random().nextInt();
        }
        Integer i = 777777777;
        integers[289] = i;

        Integer index = ParallelFindIndex.parallelFindIndex(integers, i);
        System.out.println(index);
    }
}
