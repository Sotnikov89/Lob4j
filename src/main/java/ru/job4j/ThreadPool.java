package ru.job4j;

import ru.job4j.producerconsumer.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

public class ThreadPool {

    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks;
    private boolean isRunning = true;

    public ThreadPool(int maxSizeOfTasks, int sizeOfThreads) {

        tasks = new SimpleBlockingQueue<>(maxSizeOfTasks);

        for (int i = 0; i<sizeOfThreads; i++) {
            threads.add( new Thread(() -> {
                while (isRunning) {
                    try {
                        Runnable runnable = tasks.poll();
                        runnable.run();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }));
        }
    }

    public void work(Runnable job) throws InterruptedException {
        if (isRunning) {
            tasks.offer(job);
        }
    }

    public void shutdown() {
        threads.forEach(Thread::start);
    }
}
