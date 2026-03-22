package ru.itche.backend.service.instructor;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itche.backend.dto.instructor.UpdateInstructorPhotoPayload;
import ru.itche.backend.dto.instructor.UpdateInstructorSportsPayload;
import ru.itche.backend.entity.valueobject.FullName;
import ru.itche.backend.entity.Instructor;
import ru.itche.backend.dto.instructor.NewInstructorPayload;
import ru.itche.backend.dto.instructor.UpdateInstructorPersonalPayload;
import ru.itche.backend.entity.auth.Role;
import ru.itche.backend.entity.auth.User;
import ru.itche.backend.repository.instructor.InstructorRepository;
import ru.itche.backend.repository.user.RoleRepository;
import ru.itche.backend.service.user.UserService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DefInstructorService implements InstructorService {

    private final InstructorRepository instructorRepository;
    private final RoleRepository roleRepository;
    private final UserService userService;

    @Override
    public Iterable<Instructor> getAll() {
        return instructorRepository.findAll();
    }

    @Override
    public Optional<Instructor> findById(Long id) {
        return instructorRepository.findById(id);
    }

    @Override
    @Transactional
    public Instructor create(NewInstructorPayload payload) {
        FullName fullName = new FullName(
                payload.fullNameLastName(),
                payload.fullNameFirstName(),
                payload.fullNameMiddleName()
        );

        Role instructorRole = roleRepository.findByName("instructor")
                .orElseThrow(() -> new IllegalArgumentException("Роль instructor не найдена"));

        User user = userService.createUser(payload.login(),
                payload.password(),
                payload.email(),
                payload.phone(),
                instructorRole);

        Instructor instructor = new Instructor();
        instructor.setFullName(fullName);
        instructor.setDateOfBirth(payload.birthDate());
        instructor.setGender(payload.gender());
        instructor.setPhoto(payload.photo());
        instructor.setSkillDescription(payload.skillDescription());
        instructor.setCertificateNumber(payload.certificateNumber());
        instructor.setUser(user);

        return instructorRepository.save(instructor);
    }

    @Override
    @Transactional
    public void updatePersonal(Long id, UpdateInstructorPersonalPayload payload) {
        instructorRepository.findById(id)
                .ifPresent(instructor -> {

                    Optional.ofNullable(payload.gender())
                            .ifPresent(instructor::setGender);

                    Optional.ofNullable(payload.birthDate())
                            .ifPresent(instructor::setDateOfBirth);

                    Optional.ofNullable(payload.skillDescription())
                            .ifPresent(instructor::setSkillDescription);

                    if (payload.fullNameLastName() != null ||
                            payload.fullNameFirstName() != null ||
                            payload.fullNameMiddleName() != null) {
                        instructor.setFullName(new FullName(
                                Optional.ofNullable(payload.fullNameLastName()).orElse(instructor.getFullName().getLastName()),
                                Optional.ofNullable(payload.fullNameFirstName()).orElse(instructor.getFullName().getFirstName()),
                                Optional.ofNullable(payload.fullNameMiddleName()).orElse(instructor.getFullName().getMiddleName())
                        ));
                    }
                });
    }

    @Override
    @Transactional
    public void updatePhoto(Long id, UpdateInstructorPhotoPayload payload) {
        instructorRepository.findById(id)
                .ifPresent(instructor -> {
                    instructor.setPhoto(payload.photo());
                });
    }

    @Override
    @Transactional
    public void delete(Long id) {
//        userService.deleteUser(id);
//        instructorRepository.deleteById(id);
    }

    @Override
    public void addSports(Long id, UpdateInstructorSportsPayload payload) {

    }
}
