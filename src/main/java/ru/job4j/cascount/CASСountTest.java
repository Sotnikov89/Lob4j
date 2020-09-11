package ru.job4j.cascount;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CASСountTest {

    @Test
    public void increment3Thread() throws InterruptedException {
        CASСount<Integer> cas = new CASСount<>(0);

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

        Thread thread3 = new Thread(() -> {
            for (int i = 0; i != 10; i++) {
                cas.increment();
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();
        thread1.join();
        thread2.join();
        thread3.join();

        assertEquals(cas.get(), 30);
    }
}
