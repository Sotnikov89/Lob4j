package ru.job4j.producerconsumer;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {

    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<>();
    private final int maxQueueSize;

    public SimpleBlockingQueue(int maxQueueSize) {
        this.maxQueueSize = maxQueueSize;
    }

    public synchronized void offer(T value) throws InterruptedException {
            while (queue.size() == maxQueueSize) {
                this.wait();
            }
            this.notifyAll();
            queue.offer(value);
    }

    public synchronized T poll() throws InterruptedException {
            while (queue.isEmpty()) {
                this.wait();
            }
            this.notifyAll();
            return queue.poll();
    }

    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }
}
