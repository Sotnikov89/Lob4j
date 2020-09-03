package com.job4j.Tasl1;

public class ConcurrentOutput {
    public static void main(String []args){
        Thread another = new Thread(() ->
                System.out.println(Thread.currentThread().getName())
        );
        Thread second = new Thread(() ->
                System.out.println(Thread.currentThread().getName())
        );
        another.start();
        second.start();
    }
}
