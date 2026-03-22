package ru.itche.backend.dto.instructor;

import ru.itche.backend.entity.enums.Gender;

import java.time.LocalDate;

public record UpdateInstructorPersonalPayload(
        String fullNameLastName,
        String fullNameFirstName,
        String fullNameMiddleName,
        Gender gender,
        LocalDate birthDate,
        String skillDescription
) {
}
