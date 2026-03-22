package ru.itche.backend.entity.reference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(schema = "sporta", name = "sport_disciplines")
public class SportDisciplines {

    @Id
    @Column(name = "id", length = 11, nullable = false)
    private String id;

    @ManyToOne
    @JoinColumn(name = "id_sport_type")
    private SportTypes sportType;

    @Column(name = "name_sport_disciplines", nullable = false)
    String nameSportDisciplines;
}
