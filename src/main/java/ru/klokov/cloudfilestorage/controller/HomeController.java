package ru.klokov.cloudfilestorage.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.klokov.cloudfilestorage.model.CustomUserDetails;
import ru.klokov.cloudfilestorage.service.impl.FileServiceImpl;
import ru.klokov.cloudfilestorage.utils.BreadcrumbUtils;
import ru.klokov.cloudfilestorage.utils.MinioUtils;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class HomeController {
    private final FileServiceImpl fileService;

    @GetMapping
    public String showMainPage(@AuthenticationPrincipal CustomUserDetails userDetails,
                               @RequestParam(value = "path", required = false) String path,
                               Model model) {
        String rootDirectoryPath = MinioUtils.getUsersRootDirectoryPath(userDetails.getUserId());

        if (path == null || path.isBlank()) path = rootDirectoryPath;

        model.addAttribute("files", fileService.getUserFilesInDirectory(userDetails.getUserId(), path));
        model.addAttribute("breadcrumbs", BreadcrumbUtils.getBreadcrumbResponsesFromPath(path));

        return "home";
    }
}
