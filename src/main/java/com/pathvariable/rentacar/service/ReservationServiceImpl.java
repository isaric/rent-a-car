package com.pathvariable.rentacar.service;

import com.pathvariable.rentacar.model.Car;
import com.pathvariable.rentacar.model.Reservation;
import com.pathvariable.rentacar.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ReservationServiceImpl implements ReservationService{

    private final ReservationRepository reservationRepository;

    public ReservationServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public List<Reservation> getOverlappingReservations(ZonedDateTime start, Integer duration) {
        return reservationRepository.getByTimeAndDurationOverlap(start, duration);
    }

    @Override
    public Reservation createReservation(Car car, ZonedDateTime start, Integer duration) {
        validateReservation(start, duration);
        return reservationRepository.save(new Reservation(UUID.randomUUID(), start, duration, car));
    }

    public void validateReservation(ZonedDateTime start, Integer duration) {
        if (start.isBefore(ZonedDateTime.now())) {
            throw new IllegalArgumentException("Reservation cannot be made in the past");
        }
        if (ZonedDateTime.now().plusHours(24).isBefore(start)){
            throw new IllegalArgumentException("Reservation can be made up to 24 hours ahead");
        }
        if (duration > 120) {
            throw new IllegalArgumentException("Reservation can be made for maximum 2 hours");
        }
    }

    @Override
    public void deleteReservation(UUID id) {
        reservationRepository.delete(id);
    }
}
