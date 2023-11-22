package com.pathvariable.rentacar.service;

import com.pathvariable.rentacar.model.Car;
import com.pathvariable.rentacar.repository.CarRepository;
import com.pathvariable.rentacar.repository.CarRepositoryImpl;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class CarServiceTest {
    private CarService carService;

    private CarRepository carRepository;

    @BeforeEach
    public void setUp() {
        carRepository = new CarRepositoryImpl();
        carService = new CarServiceImpl(carRepository);
    }

    @Test
    public void testCreateCarSuccess() {
        carService.createCar("C123", "Ford", "Focus");
        assertThat(carRepository.getById("C123")).isNotNull();
    }

    @Test
    public void testCreateCarFailure() {
        assertThrows(IllegalArgumentException.class, () -> carService.createCar("123", "Ford", "Focus"));
    }

    @Test
    public void testGetCarSuccess() {
        carService.createCar("C123", "Ford", "Focus");
        carService.createCar("C124", "Ford", "Focus");
        assertThat(carService.getCar("C123")).isNotNull();
    }

    @Test
    public void testGetAll() {
        carService.createCar("C123", "Ford", "Focus");
        carService.createCar("C124", "Ford", "Focus");
        assertThat(carService.getAll()).has(new Condition<>(c -> c.size() == 2, "2 cars in repository"));
    }

    @Test
    public void testUpdateCarSuccess() {
        carService.createCar("C123", "Ford", "Focus");
        carService.updateCar(new Car("C123", "Ford", "Fiesta"));
        assertThat(carRepository.getById("C123").model()).isEqualTo("Fiesta");
    }

    @Test
    public void testUpdateCarFailure() {
        carService.createCar("C123", "Ford", "Focus");
        assertThrows(IllegalStateException.class, () -> carService.updateCar(new Car("C124", "Ford", "Fiesta")));
    }

    @Test
    public void testDeleteCarSuccess() {
        carService.createCar("C123", "Ford", "Focus");
        carService.deleteCar("C123");
        assertThat(carRepository.getAll()).has(new Condition<>(List::isEmpty, "0 cars in repository"));
    }
}
