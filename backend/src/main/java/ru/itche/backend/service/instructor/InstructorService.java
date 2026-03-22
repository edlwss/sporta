package ru.itche.backend.service.instructor;

import ru.itche.backend.dto.instructor.UpdateInstructorPhotoPayload;
import ru.itche.backend.dto.instructor.UpdateInstructorSportsPayload;
import ru.itche.backend.entity.Instructor;
import ru.itche.backend.dto.instructor.NewInstructorPayload;
import ru.itche.backend.dto.instructor.UpdateInstructorPersonalPayload;

import java.util.Optional;

public interface InstructorService {

    Iterable<Instructor> getAll();
    Optional<Instructor> findById(Long id);
    Instructor create(NewInstructorPayload payload);
    void updatePersonal(Long id, UpdateInstructorPersonalPayload payload);
    void updatePhoto(Long id, UpdateInstructorPhotoPayload payload);
    void delete(Long id);

    void addSports(Long id, UpdateInstructorSportsPayload payload);
}
