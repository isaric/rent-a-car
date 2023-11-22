package com.pathvariable.rentacar.facade;

import com.pathvariable.rentacar.dto.ReservationDTO;

import java.util.UUID;

public interface ReservationFacade {

    ReservationDTO makeReservation(ReservationDTO reservation);
    void deleteReservation(UUID id);
}
