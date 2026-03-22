package ru.itche.backend.entity.auth;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
@Table(schema = "sporta", name = "roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_seq")
    @SequenceGenerator(name = "role_seq", sequenceName = "sporta.role_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "name_role", nullable = false)
    private String name;
}

