package ru.itche.backend.service.lookup;
import ru.itche.backend.dto.lookup.ImportResult;
import ru.itche.backend.entity.reference.SportTypes;


public interface SportService {
    Iterable<SportTypes> getSports();

    ImportResult importFromCsv(String resourcePath);
}
