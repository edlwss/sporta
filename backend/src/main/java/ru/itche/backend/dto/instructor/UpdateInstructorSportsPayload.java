package ru.itche.backend.dto.instructor;

import java.util.List;

public record UpdateInstructorSportsPayload(
        List<Long> sportIds
) {}