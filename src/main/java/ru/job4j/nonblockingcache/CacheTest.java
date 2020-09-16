package ru.job4j.nonblockingcache;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CacheTest {

    @Test
    public void updateTest() throws InterruptedException {

        NonBlockingCache nonBlockingCache = new NonBlockingCache();

        NonBlockingCache.Base base = new NonBlockingCache.Base(1);
        nonBlockingCache.add(base);

        Thread thread1 = new Thread(() -> {
            try {
                nonBlockingCache.update(new NonBlockingCache.Base(1));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                nonBlockingCache.update(new NonBlockingCache.Base(1));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        assertThat(base.getVersion(), is(2));
    }
}
