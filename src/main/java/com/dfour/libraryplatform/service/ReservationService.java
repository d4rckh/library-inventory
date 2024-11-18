package com.dfour.libraryplatform.service;

import com.dfour.libraryplatform.domain.dto.filters.ReservationFilterDto;
import com.dfour.libraryplatform.domain.dto.stats.ReservationStatsDto;
import com.dfour.libraryplatform.exception.NotFoundException;
import com.dfour.libraryplatform.repository.ReservationRepository;
import com.dfour.libraryplatform.entity.ReservationEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservations;

    public List<ReservationEntity> findAll() {
        return reservations.findAll();
    }

    public Optional<ReservationEntity> findById(Long id) {
        return reservations.findById(id);
    }

    public ArrayList<ReservationEntity> findByItemId(Long itemId) {
        return reservations.findAllByItemId(itemId);
    }

    public ArrayList<ReservationEntity> findByUserId(Long userId) {
        return reservations.findAllByUserId(userId);
    }

    public boolean isValid(ReservationEntity reservation) {
        return Objects.isNull(reservation.getExpiredAt()) &&
                reservation.getExpiresAt().isAfter(OffsetDateTime.now(ZoneOffset.UTC)) &&
                !reservation.isCancelled();
    }

    public Optional<ReservationEntity> getItemValidReservation(long itemId) {
        return reservations.findAllByItemId(itemId)
                .stream()
                .filter(this::isValid).findFirst();
    }

    public List<ReservationEntity> getUserValidReservation(long userId) {
        return reservations.findAllByUserId(userId)
                .stream()
                .filter(this::isValid).collect(Collectors.toList());
    }

    public void invalidateReservation(ReservationEntity reservation) {
        reservation.setExpiredAt(OffsetDateTime.now(ZoneOffset.UTC));
        reservations.save(reservation);
    }

    public ReservationEntity cancelReservation(Long reservationId) {
        ReservationEntity reservation = reservations.findById(reservationId)
                .orElseThrow(NotFoundException::new);
        reservation.setCancelled(true);
        return reservations.save(reservation);
    }

    public ReservationEntity save(ReservationEntity reservation) {
        return reservations.save(reservation);
    }

    public ReservationStatsDto reservationStats() {
        return ReservationStatsDto.builder()
                .currentReservations(reservations.countValidReservations())
                .build();
    }

    public ArrayList<ReservationEntity> findFiltered(ReservationFilterDto filters) {
        return reservations.findFiltered(filters.getUserId(), filters.getItemId(), filters.getIsActive());
    }
}