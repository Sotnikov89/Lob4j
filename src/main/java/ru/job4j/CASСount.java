package ru.job4j;

import org.junit.Test;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.Assert.assertEquals;

public class CASСount <T> {
    private final AtomicReference<Integer> count = new AtomicReference<>();

    public void increment() {
        Integer presentValue;
        Integer newValue;
        do {
            presentValue = count.get();
            if (presentValue == null) {
                throw new UnsupportedOperationException("Count is not impl.");
            }
            newValue = presentValue + 1;
        } while (!count.compareAndSet(presentValue,newValue));
    }

    public int get() {
        return Optional.of(count.get()).orElseThrow(() -> new UnsupportedOperationException("Count is not impl."));
    }

    @Test
    public void increment2Thread() throws InterruptedException {
        CASСount<Integer> cas = new CASСount<>();
        cas.count.set(0);

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i != 10; i++) {
                cas.increment();
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i != 10; i++) {
                cas.increment();
            }
        });

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        assertEquals(cas.get(), 20);
    }
}
