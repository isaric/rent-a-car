package com.pathvariable.rentacar.dto;

import com.pathvariable.rentacar.model.Reservation;
import org.springframework.lang.NonNull;

import java.time.ZonedDateTime;

public record ReservationDTO(String id, @NonNull ZonedDateTime start, @NonNull Integer duration) {
    public static ReservationDTO fromModel(Reservation reservation) {
        return new ReservationDTO(reservation.id().toString(), reservation.start(), reservation.duration());
    }
}
