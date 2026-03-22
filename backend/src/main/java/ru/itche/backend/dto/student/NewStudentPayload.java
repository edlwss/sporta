package ru.itche.backend.dto.student;

import ru.itche.backend.entity.enums.Gender;

import java.time.LocalDate;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public record NewStudentPayload(

        @NotBlank(message = "Login cannot be empty")
        @Size(min = 3, max = 50, message = "Login must be between 3 and 50 characters")
        String login,

        @NotBlank(message = "Password cannot be empty")
        @Size(min = 6, max = 100, message = "Password must be at least 6 characters")
        String password,

        @NotBlank(message = "Email cannot be empty")
        @Email(message = "Invalid email format")
        String email,

        @NotBlank(message = "Phone cannot be empty")
        @Pattern(
                regexp = "^\\+?[0-9]{10,15}$",
                message = "Phone must contain 10-15 digits"
        )
        String phone,

        @NotBlank(message = "Last name cannot be empty")
        @Size(max = 100)
        String fullNameLastName,

        @NotBlank(message = "First name cannot be empty")
        @Size(max = 100)
        String fullNameFirstName,

        @Size(max = 100)
        String fullNameMiddleName,

        @NotNull(message = "Gender must be specified")
        Gender gender,

        @NotNull(message = "Birth date must be specified")
        @Past(message = "Birth date must be in the past")
        LocalDate birthDate,

        String photo

) {

    public NewStudentPayload {
        if (photo != null && photo.isBlank()) {
            photo = null;
        }
    }
}
