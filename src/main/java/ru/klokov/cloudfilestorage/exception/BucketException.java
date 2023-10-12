package ru.klokov.cloudfilestorage.exception;

public class BucketException extends RuntimeException {
    public BucketException(String message) {
        super(message);
    }
}
