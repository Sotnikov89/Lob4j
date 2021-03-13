package ru.job4j.others;

import ru.job4j.producerconsumer.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

public class ThreadPool {

    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks;
    private volatile boolean isRunning = true;

    public ThreadPool(int maxSizeOfTasks, int sizeOfThreads) {

        tasks = new SimpleBlockingQueue<>(maxSizeOfTasks);

        for (int i = 0; i < sizeOfThreads; i++) {
            threads.add(new Thread(() -> {
                while (isRunning || !tasks.isEmpty()) {
                    try {
                        tasks.poll().run();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }));
        }
        for (Thread thread : threads) {
            thread.start();
        }
    }

    public void work(Runnable job) throws InterruptedException {
        if (isRunning) {
            tasks.offer(job);
        }
    }

    public void shutdown() {
        isRunning = false;
        for (Thread thread: threads) {
            thread.interrupt();
        }
    }
}
