package ru.itche.backend.dto.user;

public record UpdateUserPasswordPayload(
        String oldPassword,
        String newPassword
) {
}
