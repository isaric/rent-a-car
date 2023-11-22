package com.pathvariable.rentacar.dto;

import com.pathvariable.rentacar.model.Car;
import org.springframework.lang.NonNull;

public record CarDTO(@NonNull String id, String make, String model) {

    public static CarDTO fromModel(Car car) {
        return new CarDTO(car.id(), car.make(), car.model());
    }
}
