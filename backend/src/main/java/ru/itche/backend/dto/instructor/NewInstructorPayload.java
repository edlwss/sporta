package ru.itche.backend.dto.instructor;

import ru.itche.backend.entity.enums.Gender;

import java.time.LocalDate;
import java.util.List;

public record NewInstructorPayload(
        String login,
        String password,
        String email,
        String phone,

        String fullNameLastName,
        String fullNameFirstName,
        String fullNameMiddleName,
        String photo,
        Gender gender,
        LocalDate birthDate,
        List<Long> sportIds,

        String skillDescription,
        String certificateNumber
) {
    public NewInstructorPayload {
        if (photo != null && photo.isBlank()) {
            photo = null;
        }
    }
}

