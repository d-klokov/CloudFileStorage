package ru.klokov.cloudfilestorage.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.klokov.cloudfilestorage.dto.SignUpRequest;
import ru.klokov.cloudfilestorage.exception.UserAlreadyExistsException;
import ru.klokov.cloudfilestorage.model.User;
import ru.klokov.cloudfilestorage.repository.UserRepository;
import ru.klokov.cloudfilestorage.service.AuthService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void signUp(SignUpRequest request) {
        Optional<User> user = userRepository.findByEmail(request.getUsername());

        if (user.isPresent())
            throw new UserAlreadyExistsException("User with email " + request.getUsername() + " already exists!");

        userRepository.save(new User(request.getUsername(), passwordEncoder.encode(request.getPassword())));
    }
}
