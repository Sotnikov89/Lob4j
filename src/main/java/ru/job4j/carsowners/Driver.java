package ru.job4j.carsowners;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.util.List;

public class Driver {
    private int id;
    private String firstName;
    private String lastName;
    @ManyToMany (mappedBy = "driver", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    private List<Car> cars;
}
