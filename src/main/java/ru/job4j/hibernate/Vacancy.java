package ru.job4j.hibernate;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "vacancy")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vacancy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int minimumExp;
    private int averageSal;
    @OneToOne(mappedBy = "vacancy", fetch = FetchType.LAZY)
    private Candidate candidate;
    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private VacanciesBase vacanciesBase;
}
