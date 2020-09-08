package ru.job4j.producerconsumer;

public class SimpleBlockingQueueTest {

    public static void main(String[] args) throws InterruptedException{
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>();

        System.out.println("Программа запущена");

        Thread producer1 = new Thread(() -> {
            try {
                queue.offer(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Producer1");
        Thread consumer1 = new Thread(() -> {
            try {
                queue.poll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Сonsumer1");

        consumer1.start();
        producer1.start();
    }
}
