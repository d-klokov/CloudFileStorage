package ru.klokov.cloudfilestorage.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.klokov.cloudfilestorage.dto.FileUploadRequest;
import ru.klokov.cloudfilestorage.exception.FileUploadRequestException;
import ru.klokov.cloudfilestorage.util.ValidationUtils;

@Controller
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileController {
    @PostMapping
    public String uploadFile(@ModelAttribute @Valid FileUploadRequest fileUploadRequest,
                             BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            throw new FileUploadRequestException(ValidationUtils.getErrorMessageFromBindingResult(bindingResult));

        System.out.println(fileUploadRequest.getFile().getOriginalFilename());

        return "home";
    }
}
