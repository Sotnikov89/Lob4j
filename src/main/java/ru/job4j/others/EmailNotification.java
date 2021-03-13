package ru.job4j.others;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmailNotification {
    ExecutorService pool = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors());

    public void emailTo(User user) {
        pool.submit(new Runnable() {
            @Override
            public void run() {
                String subject = "Notification " + user.userName + " to email " + user.email;
                String body = "Add a new event to " + user.userName;
                send(subject, body, user.email);
            }
        });
    }

    private void close() {
        pool.shutdown();
    }

    public void send(String subject, String body, String email) {

    }

    public static class User {
        private String userName;
        private String email;
    }
}
