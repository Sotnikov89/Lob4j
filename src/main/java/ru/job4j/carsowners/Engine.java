package ru.job4j.carsowners;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

public class Engine {
    private int id;
    private String name;
    @OneToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "car_id")
    private Car car;
}
