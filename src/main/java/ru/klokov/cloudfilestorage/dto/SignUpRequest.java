package ru.klokov.cloudfilestorage.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SignUpRequest {
    @NotBlank(message = "Email must be not blank!")
    @Email(message = "Please provide a valid email address!")
    private String username;
    @NotBlank(message = "Password must be not blank!")
    @Size(min = 3, max = 20, message = "Password size must be between 3 and 20")
    private String password;
}
