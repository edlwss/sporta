package ru.itche.backend.repository.lookup;

import org.springframework.data.repository.CrudRepository;
import ru.itche.backend.entity.reference.SportDisciplines;

public interface SportDisciplinesRepository extends CrudRepository<SportDisciplines, String> {
}
