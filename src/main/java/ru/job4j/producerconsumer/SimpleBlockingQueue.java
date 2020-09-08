package ru.job4j.producerconsumer;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {

    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<>();

    public synchronized void offer(T value) throws InterruptedException {
        Thread.sleep(1000); // With this pause you have 100% guaranteed to see a consumer's massage about empty queue.
        System.out.println(Thread.currentThread().getName()
                + " - Добавляю в очередь объект - " + value + ".");
        if (queue.offer(value)) {
            System.out.println(Thread.currentThread().getName()
                    + " - Объект добавлен. Возвращаю всех потребителей в работу.");
            this.notifyAll();
        } else {
            throw new RuntimeException("Не удалось добавить объект в очередь");
        }
    }

    public synchronized T poll() throws InterruptedException {
        while (queue.size() == 0){
            System.out.println(Thread.currentThread().getName()
                    + " - В очереди нет объектов. Перехожу в состояние "
                    + Thread.State.WAITING + ".");
            this.wait();
        }
        System.out.println(Thread.currentThread().getName()
                + " - Беру объект из очереди.");
        T t = queue.poll();
        System.out.println(Thread.currentThread().getName()
                + " - Объект - " + t + ", получен.");
        return t;
    }

    public synchronized Queue<T> getQueue() {
        return queue;
    }
}
