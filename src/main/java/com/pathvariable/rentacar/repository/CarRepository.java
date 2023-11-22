package com.pathvariable.rentacar.repository;

import com.pathvariable.rentacar.model.Car;

import java.util.List;
import java.util.Optional;

public interface CarRepository {

    Car save(Car car);

    void deleteCar(String id);

    List<Car> getAll();

    Car getById(String id);

    Optional<Car> getByIdNotIn(List<String> ids);

}
