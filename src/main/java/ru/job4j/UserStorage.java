package ru.job4j;

import lombok.EqualsAndHashCode;
import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Map;

@ThreadSafe
public class UserStorage {

    private final Map<Integer, User> usersDb = new HashMap<>();

    boolean add(User user) {
        synchronized(user) {
            if (usersDb.containsKey(user.getId())) {
                System.out.println("User id already exist");
                return false;
            } else {
                usersDb.put(user.getId(), user);
                return true;
            }
        }
    }

    boolean update(User user) {
            if (usersDb.containsKey(user.getId())) {
                synchronized (usersDb.get(user.getId())) {
                    usersDb.put(user.getId(), user);
                }
                return true;
            } else {
                System.out.println("User not found");
                return false;
            }
    }

    boolean delete(User user) {
            if (usersDb.containsKey(user.getId())) {
                synchronized (usersDb.get(user.getId())) {
                    usersDb.remove(user.getId());
                }
                return true;
            } else {
                System.out.println("User not found");
                return false;
            }
        }

    synchronized boolean transfer(int fromId, int toId, int amount) {
        if (usersDb.containsKey(fromId) & usersDb.containsKey(toId)){
            User userForm = usersDb.get(fromId);
            User userToId = usersDb.get(toId);
            if (userForm.getAmount() >= amount) {
                userForm.setAmount(userForm.getAmount() - amount);
                userToId.setAmount(userToId.getAmount() + amount);
                return true;
            } else {
                System.out.println("Not enough money");
                return false;
            }
        } else {
            System.out.println("Users not found");
            return false;
        }
    }

    @EqualsAndHashCode
    @ThreadSafe
    public static class User {
        @GuardedBy("this")
        private final int id;
        @GuardedBy("this")
        private int amount;

        public User(int id, int amount) {
            this.id = id;
            this.amount = amount;
        }


        public int getId() {
            return id;
        }

        public synchronized int getAmount() {
            return amount;
        }

        public synchronized void setAmount(int amount) {
            this.amount = amount;
        }
    }
}
