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

    private synchronized boolean userExist(User user) {
        return usersDb.containsKey(user.getId());
    }

    public synchronized boolean add(User user) {
        if (!userExist(user)) {
            usersDb.put(user.getId(), user);
            return true;
        }
        return false;
    }

    public synchronized boolean update(User user) {
        if (userExist(user)) {
            usersDb.put(user.getId(), user);
            return true;
        }
        return false;
    }

    public synchronized boolean delete(User user) {
        if (userExist(user)) {
            usersDb.remove(user.getId());
            return true;
        }
        return false;
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        if (usersDb.get(fromId).amount >= amount
                && usersDb.containsKey(toId)) {

            User userFrom = usersDb.get(fromId);
            User userTo = usersDb.get(toId);

            userFrom.setAmount(userFrom.getAmount() - amount);
            userTo.setAmount(userTo.getAmount() + amount);
            return true;
            }
        return false;
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
