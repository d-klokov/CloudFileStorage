package ru.klokov.cloudfilestorage.service.impl;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import ru.klokov.cloudfilestorage.exception.UserAlreadyExistsException;
import ru.klokov.cloudfilestorage.model.Role;
import ru.klokov.cloudfilestorage.model.User;
import ru.klokov.cloudfilestorage.repository.UserRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
            "postgres:15-alpine"
    );

    @BeforeAll
    static void beforeAll() {
        postgres.start();
    }

    @AfterAll
    static void afterAll() {
        postgres.stop();
    }

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
    }

    @Test
    @DisplayName("Should save user")
    void saveUserSuccess() {
        String email = "hello@gmail.com";
        String password = "password";

        userService.saveUser(new User(email, password));

        Optional<User> expectedUser = userRepository.findByEmail(email);

        assertEquals(userRepository.count(), 1);
        assertTrue(expectedUser.isPresent());
        assertEquals(expectedUser.get().getEmail(), email);
        assertEquals(expectedUser.get().getPassword(), password);
    }

    @Test
    @DisplayName("Should throw UserAlreadyExistsException")
    void saveUserUserAlreadyExistsException() {
        String email = "hello@gmail.com";
        String password = "password";

        userService.saveUser(new User(email, password));

        assertThrows(UserAlreadyExistsException.class, () -> userService.saveUser(new User(email, password)));
    }
}