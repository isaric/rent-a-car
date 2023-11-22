package com.pathvariable.rentacar.service;

import com.pathvariable.rentacar.model.Car;

import java.util.List;
import java.util.Optional;

public interface CarService {

    Car createCar(String id, String make, String model);

    Car getCar(String id);

    List<Car> getAll();

    Car updateCar(Car car);

    void deleteCar(String id);

    Optional<Car> getFreeCar(List<String> reservedIds);
}
