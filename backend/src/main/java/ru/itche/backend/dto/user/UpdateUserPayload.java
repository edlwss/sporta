package ru.itche.backend.dto.user;

public record UpdateUserPayload(
        String login,
        String email,
        String phone
) {
}
