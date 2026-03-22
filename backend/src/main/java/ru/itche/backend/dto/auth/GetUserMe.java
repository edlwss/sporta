package ru.itche.backend.dto.auth;

import ru.itche.backend.entity.auth.User;

public record GetUserMe(
        Long id,
        String role
) {
    public static GetUserMe fromUser(User user) {
        return new GetUserMe(
                user.getId(),
                user.getRole().getName()
        );
    }
}
