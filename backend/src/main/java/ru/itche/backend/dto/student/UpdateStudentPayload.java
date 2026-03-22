package ru.itche.backend.dto.student;

import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import ru.itche.backend.entity.enums.Gender;

import java.time.LocalDate;

public record UpdateStudentPayload(
        @Size(max = 100, message = "Last name must not exceed 100 characters")
        String fullNameLastName,

        @Size(max = 100, message = "First name must not exceed 100 characters")
        String fullNameFirstName,

        @Size(max = 100, message = "Middle name must not exceed 100 characters")
        String fullNameMiddleName,

        @Size(max = 255, message = "Photo URL must not exceed 255 characters")
        String photo,

        Gender gender,

        @Past(message = "Birth date must be in the past")
        LocalDate birthDate
) {
}
