package ru.job4j;

import ru.job4j.producerconsumer.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

public class ThreadPool {

    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks;

    public ThreadPool(int maxSizeOfTasks, int sizeOfThreads) {
        tasks = new SimpleBlockingQueue<>(maxSizeOfTasks);
        for (int i = 0; i<sizeOfThreads; i++) {
            threads.add( new Thread(() -> {
                try {
                    tasks.poll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }));
        }
    }

    public void work(Runnable job) throws InterruptedException {
        tasks.offer(job);
    }

    public void shutdown() {
        threads.forEach(Thread::start);
    }
}
