package ru.job4j.nonblockingcache;

import java.util.concurrent.ConcurrentHashMap;

public class NonBlockingCache {
    private final ConcurrentHashMap<Integer, Base> bases = new ConcurrentHashMap<>();

    public Base add(Base model) {
        return bases.putIfAbsent(model.id, model);
    }

    public Base update(Base model) {
        return bases.computeIfPresent(model.id, (id, base) -> {
            if (model.version != base.version) {
                throw new OptimisticException();
            }
            model.version += 1;
            return model;
        });
    }

    public void delete(Base model) {
        bases.remove(model.id);
    }

    public static class Base {

        private final int id;
        private int version = 0;

        public Base(int id) {
            this.id = id;
        }

        public int getVersion() {
            return version;
        }
    }
}
