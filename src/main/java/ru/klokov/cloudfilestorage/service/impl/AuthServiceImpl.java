package ru.klokov.cloudfilestorage.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.klokov.cloudfilestorage.dto.SignUpRequest;
import ru.klokov.cloudfilestorage.model.User;
import ru.klokov.cloudfilestorage.service.AuthService;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserServiceImpl userService;
    private final PasswordEncoder passwordEncoder;
    private final DirectoryServiceImpl directoryService;

    @Override
    public void signUp(SignUpRequest request) {
        User user = new User(request.getUsername(), passwordEncoder.encode(request.getPassword()));

        userService.saveUser(user);

        directoryService.createUsersRootDirectory(user.getId());
    }
}
