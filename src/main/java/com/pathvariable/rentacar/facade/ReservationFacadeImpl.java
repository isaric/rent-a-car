package com.pathvariable.rentacar.facade;

import com.pathvariable.rentacar.dto.ReservationDTO;
import com.pathvariable.rentacar.model.Car;
import com.pathvariable.rentacar.model.Reservation;
import com.pathvariable.rentacar.service.CarService;
import com.pathvariable.rentacar.service.ReservationService;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class ReservationFacadeImpl implements ReservationFacade{

    private final CarService carService;

    private final ReservationService reservationService;

    public ReservationFacadeImpl(CarService carService, ReservationService reservationService) {
        this.carService = carService;
        this.reservationService = reservationService;
    }

    @Override
    public ReservationDTO makeReservation(ReservationDTO reservation) {
        return ReservationDTO.fromModel(makeReservation(reservation.start(), reservation.duration()));
    }

    private Reservation makeReservation(ZonedDateTime start, Integer duration) {
        reservationService.validateReservation(start, duration);
        List<Reservation> overlapping = reservationService.getOverlappingReservations(start, duration);
        List<String> overlappingCars = overlapping.stream().map(Reservation::car).map(Car::id).toList();
        Optional<Car> freeCar = carService.getFreeCar(overlappingCars);
        if (freeCar.isEmpty()) {
            throw new IllegalStateException("No available cars");
        }
        return reservationService.createReservation(freeCar.get(), start, duration);
    }

    @Override
    public void deleteReservation(UUID id) {
        reservationService.deleteReservation(id);
    }
}
