package com.pathvariable.rentacar.facade;

import com.pathvariable.rentacar.dto.ReservationDTO;
import com.pathvariable.rentacar.repository.ReservationRepository;
import com.pathvariable.rentacar.service.CarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.ZonedDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

@SpringBootTest
public class ReservationFacadeTest {


    @Autowired
    private ReservationFacade reservationFacade;

    @Autowired
    private CarService carService;

    @Autowired
    private ReservationRepository reservationRepository;


    @BeforeEach
    public void setUp() {
        carService.getAll().forEach(car -> carService.deleteCar(car.id()));
        carService.createCar("C123", "Ford", "Focus");
        carService.createCar("C124", "Ford", "Focus");
    }

    @Test
    public void testMakeReservationSuccess() {
        reservationFacade.makeReservation(new ReservationDTO(null, ZonedDateTime.now().plusHours(1), 60));
    }

    @Test
    public void testMakeReservationFailure() {
        carService.deleteCar("C123");
        carService.deleteCar("C124");
        assertThatExceptionOfType(IllegalStateException.class)
                .isThrownBy(() -> reservationFacade.makeReservation(new ReservationDTO(null, ZonedDateTime.now().plusHours(1), 60)));
    }

    @Test
    public void testDeleteReservationSuccess() {
        String id = reservationFacade.makeReservation(new ReservationDTO(null, ZonedDateTime.now().plusHours(1), 60)).id();
        UUID uuid = UUID.fromString(id);
        assertThat(reservationRepository.getById(uuid)).isNotNull();
        reservationFacade.deleteReservation(uuid);
        assertThat(reservationRepository.getById(uuid)).isNull();
    }
}
