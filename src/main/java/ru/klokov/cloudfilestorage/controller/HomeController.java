package ru.klokov.cloudfilestorage.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.klokov.cloudfilestorage.model.CustomUserDetails;
import ru.klokov.cloudfilestorage.model.User;
import ru.klokov.cloudfilestorage.service.UserService;
import ru.klokov.cloudfilestorage.service.impl.MinioServiceImpl;
import ru.klokov.cloudfilestorage.util.MinioUtil;

@Controller
@RequestMapping("/home")
@RequiredArgsConstructor
public class HomeController {
    private final MinioServiceImpl minioService;

    @GetMapping
    public String showMainPage(@AuthenticationPrincipal CustomUserDetails userDetails, Model model) {
        model.addAttribute("rootDirectoryPath", MinioUtil.getUsersRootFolderPath(userDetails.getUserId()));
        model.addAttribute("files", minioService.getAllUserFiles(userDetails.getUserId()));

        return "home";
    }
}
