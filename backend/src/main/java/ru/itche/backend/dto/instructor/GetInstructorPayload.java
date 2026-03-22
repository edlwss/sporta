package ru.itche.backend.dto.instructor;

import ru.itche.backend.entity.Instructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public record GetInstructorPayload(
        String fullNameLastName,
        String fullNameFirstName,
        String fullNameMiddleName,
        String fullName,
        String gender,
        LocalDate birthDate,
        String photo,

        String skillDescription,
        String certificateNumber,
        Long user_id
) {

    public static GetInstructorPayload from(Instructor instructor) {

        return new GetInstructorPayload(
                instructor.getFullName().getLastName(),
                instructor.getFullName().getFirstName(),
                instructor.getFullName().getMiddleName(),
                instructor.getFullName().getFullName(),
                instructor.getGender().name(),
                instructor.getDateOfBirth(),
                instructor.getPhoto(),

                instructor.getSkillDescription(),
                instructor.getCertificateNumber(),
                instructor.getUser().getId()
        );
    }

    public static List<GetInstructorPayload> fromList(Iterable<Instructor> instructors) {
        List<GetInstructorPayload> list = new ArrayList<>();
        for (Instructor instructor : instructors) {
            list.add(from(instructor));
        }
        return list;
    }

}
