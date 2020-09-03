package com.job4j.concurrent.Task1_1;

public class ThreadState {
    public static void main(String[] args) throws InterruptedException {
        Thread first = new Thread(()->{}
        );
        Thread second = new Thread(()->{}
        );

        System.out.println(first.getName()+" "+first.getState());
        System.out.println(second.getName()+" "+second.getState());

        first.start();
        second.start();

        while (first.getState()!= Thread.State.TERMINATED &
                second.getState()!= Thread.State.TERMINATED){

            System.out.println(first.getName()+" "+first.getState());
            System.out.println(second.getName()+" "+second.getState());
        }

        first.join();
        second.join();

        System.out.println("\nMassage from the "+Thread.currentThread().getName()+"\n"+
                first.getName()+" "+first.getState()+"\n"+
                second.getName()+" "+second.getState()+"\n"+
                "Work is done");
    }
}
