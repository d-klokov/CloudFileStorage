package ru.klokov.cloudfilestorage.service;

import ru.klokov.cloudfilestorage.dto.SignUpRequest;

public interface AuthService {
    void signUp(SignUpRequest request);
}
