package ru.job4j.carsowners;

import javax.persistence.*;
import java.util.List;

public class Car {
    private int id;
    private String model;
    @OneToOne (mappedBy = "car", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Engine engine;
    @ManyToMany (cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable (name = "history_owner",
            joinColumns = @JoinColumn(name = "car_id"),
            inverseJoinColumns = @JoinColumn(name = "driver_id")
    )
    private List<Driver> drivers;
}
