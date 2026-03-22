package ru.itche.backend.repository.lookup;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.itche.backend.entity.reference.SportTypes;

import java.util.List;


public interface SportTypesRepository extends CrudRepository<SportTypes, String> {
}
