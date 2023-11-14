package ru.klokov.cloudfilestorage.exception;

import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.view.RedirectView;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = UserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String showSignUpPageWithError(Exception ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        return "signup";
    }

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
