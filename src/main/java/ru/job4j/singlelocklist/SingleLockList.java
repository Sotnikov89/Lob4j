package ru.job4j.singlelocklist;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@ThreadSafe
public class SingleLockList<T> implements Iterable<T> {

    @GuardedBy("this")
    private List<T> list = new ArrayList<>();

    public void add(T value) {
    }

    public T get(int index) {
        return null;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }
}
