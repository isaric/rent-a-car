package com.pathvariable.rentacar.repository;

import com.pathvariable.rentacar.model.Car;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class CarRepositoryImpl implements CarRepository{

    private final ConcurrentHashMap<String, Car> repo;

    public CarRepositoryImpl() {
        this.repo = new ConcurrentHashMap<>();
    }

    @Override
    public Car save(Car car) {
        repo.put(car.id(), car);
        return car;
    }

    @Override
    public void deleteCar(String id) {
        repo.remove(id);
    }

    @Override
    public List<Car> getAll() {
        return repo.values().stream().toList();
    }

    @Override
    public Car getById(String id) {
        return repo.get(id);
    }

    @Override
    public Optional<Car> getByIdNotIn(List<String> ids) {
        return repo.keySet().stream()
                .filter(id -> !ids.contains(id))
                .findFirst()
                .map(repo::get);
    }
}
