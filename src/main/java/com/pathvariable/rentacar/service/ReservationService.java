package com.pathvariable.rentacar.service;

import com.pathvariable.rentacar.model.Car;
import com.pathvariable.rentacar.model.Reservation;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

public interface ReservationService {


    Reservation createReservation(Car car, ZonedDateTime start, Integer duration);

    List<Reservation> getOverlappingReservations(ZonedDateTime start, Integer duration);

    void validateReservation(ZonedDateTime start, Integer duration);

    void deleteReservation(UUID id);

}
