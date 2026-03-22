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
@Table(schema = "sporta", name = "students")
public class Student {

    @Id
    private Long id;

    @OneToOne(optional = false, fetch = FetchType.EAGER)
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

    @Column(name = "photo")
    private String photo;

}
