package ru.klokov.cloudfilestorage.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.klokov.cloudfilestorage.dto.DirectoryUploadRequest;
import ru.klokov.cloudfilestorage.exception.DirectoryUploadRequestException;
import ru.klokov.cloudfilestorage.model.CustomUserDetails;
import ru.klokov.cloudfilestorage.service.DirectoryService;
import ru.klokov.cloudfilestorage.utils.ValidationUtils;

@Controller
@RequestMapping("/directory")
@RequiredArgsConstructor
public class DirectoryController {
    private final DirectoryService directoryService;

    @PostMapping
    public String uploadDirectory(@AuthenticationPrincipal CustomUserDetails userDetails,
                                  @ModelAttribute @Valid DirectoryUploadRequest directoryUploadRequest,
                                  BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            throw new DirectoryUploadRequestException(ValidationUtils.getErrorMessageFromBindingResult(bindingResult));

        directoryService.uploadDirectory(userDetails.getUserId(), directoryUploadRequest.getDirectoryFiles());

        return "redirect:/";
    }
}
