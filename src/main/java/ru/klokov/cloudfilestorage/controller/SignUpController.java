package ru.klokov.cloudfilestorage.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.klokov.cloudfilestorage.dto.SignUpRequest;
import ru.klokov.cloudfilestorage.exception.UserAlreadyExistsException;
import ru.klokov.cloudfilestorage.service.AuthService;

@Controller
@RequestMapping("/signup")
@RequiredArgsConstructor
public class SignUpController {
    private final AuthService authService;

    @GetMapping
    public String showSignUpPage(Model model) {
        model.addAttribute("signUpRequest", new SignUpRequest());

        return "signup";
    }

    @PostMapping
    public String signUp(@ModelAttribute("signUpRequest") @Valid SignUpRequest signUpRequest) {
        authService.signUp(signUpRequest);

        return "redirect:/signin";
    }

    @ExceptionHandler(value = UserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String showSignUpPageWithError(Exception ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        return "signup";
    }
}
