package ru.klokov.cloudfilestorage.exception;

public class MinioFileException extends RuntimeException {
    public MinioFileException(String message) {
        super(message);
    }
}
