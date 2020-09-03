package com.job4j.concurrent.Task3;

public class ConsoleProgress implements Runnable {

    @Override
    public void run() {

        int i=0;
        String[] process = new String[]{"-","\\","|", "/"};

        while (!Thread.currentThread().isInterrupted()){

            try {
                System.out.print("\r Loading: "+process[i]);
                if (i == process.length-1) i = 0;
                else i++;
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new ConsoleProgress());
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }
}
