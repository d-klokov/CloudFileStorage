package ru.klokov.cloudfilestorage.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.klokov.cloudfilestorage.exception.UserAlreadyExistsException;
import ru.klokov.cloudfilestorage.model.User;
import ru.klokov.cloudfilestorage.repository.UserRepository;
import ru.klokov.cloudfilestorage.service.UserService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public void saveUser(User user) {
        Optional<User> userToSave = userRepository.findByEmail(user.getEmail());

        if (userToSave.isPresent())
            throw new UserAlreadyExistsException("User with email " + user.getEmail() + " already exists!");

        userRepository.save(user);
    }
}
