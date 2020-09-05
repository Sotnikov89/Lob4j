package ru.job4j;

public class Wget {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            int index = 0;
            do {
                System.out.print("\rLoading : " + index + "%");
                index++;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } while (index <= 100);
        });
        thread.start();
    }
}
