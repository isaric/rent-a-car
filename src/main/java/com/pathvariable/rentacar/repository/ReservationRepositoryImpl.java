package com.pathvariable.rentacar.repository;

import com.pathvariable.rentacar.model.Reservation;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;

@Repository
public class ReservationRepositoryImpl implements ReservationRepository {

    private final ConcurrentHashMap<UUID, Reservation> repo;

    public ReservationRepositoryImpl() {
        this.repo = new ConcurrentHashMap<>();
    }

    @Override
    public Reservation save(Reservation reservation) {
        repo.put(reservation.id(), reservation);
        return reservation;
    }

    @Override
    public boolean delete(UUID id) {
        return repo.remove(id) != null;
    }

    @Override
    public List<Reservation> getAll() {
        return repo.values().stream().toList();
    }

    @Override
    public Reservation getById(UUID id) {
        return repo.get(id);
    }

    @Override
    public List<Reservation> getByTimeAndDurationOverlap(ZonedDateTime start, Integer duration) {
        return repo.values().stream()
                .filter(r -> isOverlapping(start, duration).test(r))
                .toList();
    }

    private Predicate<Reservation> isOverlapping(ZonedDateTime start, Integer duration) {
        return reservation -> reservation.start().isAfter(start.minusMinutes(1)) && reservation.start().isBefore(start.plusMinutes(duration));
    }
}
