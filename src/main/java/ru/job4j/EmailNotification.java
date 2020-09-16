package ru.job4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmailNotification {
    ExecutorService pool = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors());

    public void emailTo(User user) {
        pool.submit(new Runnable() {
            @Override
            public void run() {
                String suject = "Notification " + user.userName + " to email " + user.email;
                String body = "Add a new event to " + user.userName;
                send(suject, body, user.email);
            }
        });
    }

    private void close() {
        pool.shutdown();
    }

    public void send(String suject, String body, String email) {

    }

    public static class User {
        private String userName;
        private String email;

        public User() {
        }

        public User(String userName, String email) {
            this.userName = userName;
            this.email = email;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }
}
