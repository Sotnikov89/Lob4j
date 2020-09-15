package ru.job4j.nonblockingcache;

public class OptimisticException extends RuntimeException {
    public OptimisticException() {
    }

    public OptimisticException(String message) {
        super(message);
    }

    public OptimisticException(String message, Throwable cause) {
        super(message, cause);
    }
}
