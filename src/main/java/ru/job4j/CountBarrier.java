package ru.job4j;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class CountBarrier {

    @GuardedBy("this")
    private final Object monitor = this;

    @GuardedBy("this")
    private final int total;

    @GuardedBy("this")
    private int count = 0;

    public synchronized int getTotal() {
        return total;
    }

    public synchronized int getCount() {
        return count;
    }

    public CountBarrier(final int total) {
        this.total = total;
    }

    public synchronized void count() {
        count++;
        monitor.notifyAll();
    }

    public void await() {
        synchronized (monitor) {
            while (getTotal() != getCount()) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
