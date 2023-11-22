package com.pathvariable.rentacar.service;

import com.pathvariable.rentacar.model.Car;
import com.pathvariable.rentacar.repository.CarRepository;
import com.pathvariable.rentacar.repository.CarRepositoryImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public Car createCar(String id,String make, String model) {
        validateId(id);
        Car newCar = new Car(id, make, model);
        return carRepository.save(newCar);
    }

    private void validateId(String id) {
        if (carRepository.getById(id) != null) {
            throw new IllegalStateException("Car with id " + id + " already exists");
        }
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Id cannot be empty");
        }
        if (!id.startsWith("C")) {
            throw new IllegalArgumentException("Id must start with C");
        }
    }

    @Override
    public Car getCar(String id) {
        return carRepository.getById(id);
    }

    @Override
    public List<Car> getAll() {
        return carRepository.getAll();
    }

    @Override
    public Car updateCar(Car car) {
        if (carRepository.getById(car.id()) == null) {
            throw new IllegalStateException("Car with id " + car.id() + " does not exist");
        }
        return carRepository.save(car);
    }

    @Override
    public void deleteCar(String id) {
        carRepository.deleteCar(id);
    }

    @Override
    public Optional<Car> getFreeCar(List<String> reservedIds) {
        return carRepository.getByIdNotIn(reservedIds);
    }
}
