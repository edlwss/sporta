package ru.itche.backend.dto.user;

import ru.itche.backend.entity.auth.User;

public record GetUserPayload(
        String login,
        String email,
        String phone
) {
    public static GetUserPayload from(User user){
        return new GetUserPayload(
                user.getLogin(),
                user.getEmail(),
                user.getPhone()
        );
    }
}
