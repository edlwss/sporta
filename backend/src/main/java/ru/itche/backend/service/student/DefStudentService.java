package ru.itche.backend.service.student;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itche.backend.dto.student.NewStudentPayload;
import ru.itche.backend.dto.student.UpdateStudentPayload;
import ru.itche.backend.entity.Student;
import ru.itche.backend.entity.auth.Role;
import ru.itche.backend.entity.auth.User;
import ru.itche.backend.entity.valueobject.FullName;
import ru.itche.backend.exceptions.StudentNotFoundException;
import ru.itche.backend.repository.student.StudentRepository;
import ru.itche.backend.repository.user.RoleRepository;
import ru.itche.backend.repository.user.UserRepository;
import ru.itche.backend.service.user.UserService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DefStudentService implements StudentService {

    private final StudentRepository studentRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserService userService;

    @Override
    public Iterable<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    @Transactional
    public Student createStudent(NewStudentPayload payload) {
        FullName fullName = new FullName(
                payload.fullNameLastName(),
                payload.fullNameFirstName(),
                payload.fullNameMiddleName()
        );

        Role studentRole = roleRepository.findByName("student")
                .orElseThrow(() -> new IllegalArgumentException("Роль student не найдена"));


        User user = userService.createUser(payload.login(),
                payload.password(),
                payload.email(),
                payload.phone(),
                studentRole);

        Student student = new Student();
        student.setFullName(fullName);
        student.setPhoto(payload.photo());
        student.setDateOfBirth(payload.birthDate());
        student.setGender(payload.gender());
        student.setUser(user);

        return studentRepository.save(student);
    }


    @Override
    public Optional<Student> findStudent(Long studentId) {
        return studentRepository.findById(studentId);
    }

    @Override
    @Transactional
    public void updateStudent(Long id, UpdateStudentPayload payload) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));

        mergeStudent(student, payload);
    }

    private void mergeStudent(Student student, UpdateStudentPayload payload) {
        boolean fullNameChanged =
                payload.fullNameLastName() != null ||
                        payload.fullNameFirstName() != null ||
                        payload.fullNameMiddleName() != null;

        if (fullNameChanged) {
            FullName currentFullName = student.getFullName();

            student.setFullName(new FullName(
                    payload.fullNameLastName() != null
                            ? payload.fullNameLastName()
                            : currentFullName.getLastName(),
                    payload.fullNameFirstName() != null
                            ? payload.fullNameFirstName()
                            : currentFullName.getFirstName(),
                    payload.fullNameMiddleName() != null
                            ? payload.fullNameMiddleName()
                            : currentFullName.getMiddleName()
            ));
        }

        if (payload.birthDate() != null) {
            student.setDateOfBirth(payload.birthDate());
        }

        if (payload.gender() != null) {
            student.setGender(payload.gender());
        }

        if (payload.photo() != null) {
            student.setPhoto(payload.photo().isBlank() ? null : payload.photo());
        }
    }

    @Override
    @Transactional
    public void deleteStudent(Long id) {
        studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));

        userRepository.deleteUser(id);
    }
}
