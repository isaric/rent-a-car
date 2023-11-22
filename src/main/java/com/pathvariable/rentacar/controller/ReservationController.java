package com.pathvariable.rentacar.controller;

import com.pathvariable.rentacar.dto.ErrorMessageDTO;
import com.pathvariable.rentacar.dto.ReservationDTO;
import com.pathvariable.rentacar.facade.ReservationFacade;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    private final ReservationFacade reservationFacade;

    public ReservationController(ReservationFacade reservationFacade) {
        this.reservationFacade = reservationFacade;
    }

    @PostMapping
    public ReservationDTO createReservation(@RequestBody ReservationDTO reservation) {
        return reservationFacade.makeReservation(reservation);
    }

    @DeleteMapping("/{id}")
    public void deleteReservation(@PathVariable String id) {
        reservationFacade.deleteReservation(UUID.fromString(id));
    }

    @ExceptionHandler({IllegalStateException.class, IllegalArgumentException.class})
    public ResponseEntity<ErrorMessageDTO> handleException(Exception ex) {
        return ResponseEntity.badRequest().body(new ErrorMessageDTO(ex.getMessage(), 400));
    }
}
