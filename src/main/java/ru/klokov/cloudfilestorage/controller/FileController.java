package ru.klokov.cloudfilestorage.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.klokov.cloudfilestorage.dto.FileUploadRequest;
import ru.klokov.cloudfilestorage.exception.FileUploadRequestException;
import ru.klokov.cloudfilestorage.model.CustomUserDetails;
import ru.klokov.cloudfilestorage.service.impl.FileServiceImpl;
import ru.klokov.cloudfilestorage.utils.ValidationUtils;

@Controller
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileController {
    private final FileServiceImpl fileService;
    @PostMapping
    public String uploadFile(@AuthenticationPrincipal CustomUserDetails userDetails,
                             @ModelAttribute @Valid FileUploadRequest fileUploadRequest,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            throw new FileUploadRequestException(ValidationUtils.getErrorMessageFromBindingResult(bindingResult));

        fileService.uploadFile(userDetails.getUserId(), fileUploadRequest);

        return "redirect:/";
    }
}
