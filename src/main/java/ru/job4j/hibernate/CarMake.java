package ru.job4j.hibernate;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "make")
@Data
@NoArgsConstructor
public class CarMake {

    public CarMake(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CarModel> carModels = new ArrayList<>();
    public void addCarModel (CarModel carModel) {
        this.carModels.add(carModel);
    }
}
