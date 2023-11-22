package com.pathvariable.rentacar.repository;

import com.pathvariable.rentacar.model.Reservation;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

public interface ReservationRepository {

    Reservation save(Reservation reservation);

    boolean delete(UUID id);

    List<Reservation> getAll();

    Reservation getById(UUID id);

    List<Reservation> getByTimeAndDurationOverlap(ZonedDateTime start, Integer duration);
}
