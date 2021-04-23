package ru.job4j.hibernate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "base")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VacanciesBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToMany(mappedBy = "vacanciesBase", fetch = FetchType.LAZY)
    private List<Vacancy> vacancies = new ArrayList<>();
}
