package ru.job4j.others;

public class Switcher {

    public synchronized void on() {
            this.notify();
    }

    public synchronized void check() {
        try {
            this.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {

        Switcher switcher = new Switcher();

        Thread first = new Thread(
                () -> {
                    while (true) {
                        System.out.println("Thread A");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        switcher.on();
                        switcher.check();
                    }
                }
        );
        Thread second = new Thread(
                () -> {
                    while (true) {
                        switcher.check();
                        System.out.println("Thread B");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        switcher.on();
                    }
                }
        );
        first.start();
        second.start();
        first.join();
        second.join();
    }
}
