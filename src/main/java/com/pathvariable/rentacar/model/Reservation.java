package com.pathvariable.rentacar.model;

import java.time.ZonedDateTime;
import java.util.UUID;

/**
 * The reservation model. A reservation has a start time, a duration and the car to be rented.
 * The id is a random UUID.
 * @param id - random UUID
 * @param start - start time of the reservation
 * @param duration - duration of rental period in minutes - cannot exceed 120 minutes
 * @param car - the car model to be rented
 */
public record Reservation(UUID id, ZonedDateTime start, Integer duration, Car car) {}
