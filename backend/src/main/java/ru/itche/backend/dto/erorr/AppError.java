package ru.itche.backend.dto.erorr;

import java.util.Date;

public record AppError(
        String status,
        String message,
        Date timestamp
) {
    public static AppError from(String status, String message) {
        return new AppError(
                status,
                message,
                new Date());
    }
}
