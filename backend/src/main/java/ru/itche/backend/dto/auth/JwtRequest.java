package ru.itche.backend.dto.auth;

public record JwtRequest (
        String login,
        String password ){
}
