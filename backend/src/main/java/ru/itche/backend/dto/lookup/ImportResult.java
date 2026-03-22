package ru.itche.backend.dto.lookup;

public record ImportResult(
        int sportTypesCreated,
        int disciplinesCreated,
        int skipped) {
}

