package ru.job4j;

import lombok.EqualsAndHashCode;
import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Map;

@ThreadSafe
public class UserStorage {

    @GuardedBy("this")
    private final Map<Integer, User> usersDb = new HashMap<>();

    public synchronized boolean add(User user) {
        if (usersDb.containsKey(user.getId())) {
            System.out.println("User id already exist");
            return false;
        } else {
            usersDb.put(user.getId(), user);
            return true;
        }
    }

    public synchronized boolean update(User user) {
        if (usersDb.containsKey(user.getId())) {
            usersDb.put(user.getId(), user);
            return true;
        } else {
            System.out.println("User not found");
            return false;
        }
    }

    public synchronized boolean delete(User user) {
        if (usersDb.containsKey(user.getId())) {
            usersDb.remove(user.getId());
            return true;
        } else {
            System.out.println("User not found");
            return false;
        }
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        if (usersDb.containsKey(fromId) && usersDb.containsKey(toId)){
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
    public static class User {

        private final int id;
        private int amount;

        public User(int id, int amount) {
            this.id = id;
            this.amount = amount;
        }

        public int getId() {
            return id;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }
    }
}
