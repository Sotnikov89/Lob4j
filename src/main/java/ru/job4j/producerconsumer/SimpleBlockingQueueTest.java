package ru.job4j.producerconsumer;

public class SimpleBlockingQueueTest {

    public static void main(String[] args) throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(2);

        Thread producer1 = new Thread(() -> {
            try {
                queue.offer(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread consumer1 = new Thread(() -> {
            try {
                queue.poll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        producer1.start();
        producer1.join();
        consumer1.start();
        consumer1.join();
    }
}
