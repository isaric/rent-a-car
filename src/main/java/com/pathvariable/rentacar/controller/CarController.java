package com.pathvariable.rentacar.controller;

import com.pathvariable.rentacar.dto.CarDTO;
import com.pathvariable.rentacar.dto.ErrorMessageDTO;
import com.pathvariable.rentacar.model.Car;
import com.pathvariable.rentacar.service.CarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping
    public CarDTO addCar(@RequestBody CarDTO car) {
        return CarDTO.fromModel(carService.createCar(car.id(), car.make(), car.model()));
    }

    @GetMapping()
    public List<CarDTO> getCars() {
        return carService.getAll().stream().map(CarDTO::fromModel).toList();
    }

    @GetMapping("/{id}")
    public CarDTO getCar(@PathVariable String id) {
        return CarDTO.fromModel(carService.getCar(id));
    }

    @PutMapping()
    public CarDTO updateCar(@RequestBody CarDTO car) {
        return CarDTO.fromModel(carService.updateCar(new Car(car.id(), car.make(), car.model())));
    }

    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable String id) {
        carService.deleteCar(id);
    }

    @ExceptionHandler({IllegalStateException.class, IllegalArgumentException.class})
    public ResponseEntity<ErrorMessageDTO> handleException(Exception ex) {
        return ResponseEntity.badRequest().body(new ErrorMessageDTO(ex.getMessage(), 400));
    }
}
