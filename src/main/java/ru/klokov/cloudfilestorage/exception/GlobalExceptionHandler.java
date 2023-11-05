package ru.klokov.cloudfilestorage.exception;

import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.view.RedirectView;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(FileUploadRequestException.class)
    public String handleFileUploadRequestException(FileUploadRequestException e) {
        System.out.println(e.getMessage());

        return "home";
    }

    @ExceptionHandler(SizeLimitExceededException.class)
    public String handleSizeLimitExceededException(SizeLimitExceededException e) {
        System.out.println(e.getMessage());

        return "home";
    }
}
