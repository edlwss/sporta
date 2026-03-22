package ru.itche.backend.entity.auth;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(schema = "sporta", name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "sporta.user_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "password", nullable = false) //добавить ограничение по длине и проверку на сложность
    private String password;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "number_phone", nullable = false)
    private String phone;

    @Column(name = "data_registration", nullable = false)
    private LocalDateTime registrationDate = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;
}

