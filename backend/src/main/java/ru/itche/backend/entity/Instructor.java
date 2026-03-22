package ru.itche.backend.entity;

import jakarta.persistence.*;
import lombok.*;
import ru.itche.backend.entity.auth.User;
import ru.itche.backend.entity.valueobject.FullName;
import ru.itche.backend.entity.enums.Gender;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(schema = "sporta", name = "instructors")
public class Instructor {

    @Id
    private Long id;

    @OneToOne(optional = false)
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    @Embedded
    private FullName fullName;

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "data_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @Column(name = "photo", nullable = false)
    private String photo;

    @Column(name = "skill_description", columnDefinition = "TEXT", nullable = false)
    private String skillDescription;

    @Column(name = "certificate_number", nullable = false)
    private String certificateNumber;

//    @Column(name = "data_verified", nullable = false)
//    private boolean dataVerified;
//
//    @Column(name = "official_employment", nullable = false)
//    private boolean officialEmployment;

}
