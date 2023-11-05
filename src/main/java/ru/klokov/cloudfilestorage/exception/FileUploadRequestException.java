package ru.klokov.cloudfilestorage.exception;

public class FileUploadRequestException extends RuntimeException {
    public FileUploadRequestException(String message) {
        super(message);
    }
}
