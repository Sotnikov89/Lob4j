package ru.job4j.cascount;

import java.util.concurrent.atomic.AtomicReference;

public class CasCount<T> {
    private final AtomicReference<Integer> count = new AtomicReference<>();

    public CasCount(int startValue) {
        count.set(startValue);
    }

    public void increment() {
        Integer presentValue;
        do {
            presentValue = count.get();
        } while (!count.compareAndSet(presentValue, ++presentValue));
    }

    public int get() {
        return count.get();
    }
}
