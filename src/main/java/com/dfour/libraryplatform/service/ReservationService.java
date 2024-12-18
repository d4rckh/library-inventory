package com.dfour.libraryplatform.service;

import com.dfour.libraryplatform.domain.dto.filters.ReservationFilterDto;
import com.dfour.libraryplatform.domain.dto.stats.ReservationStatsDto;
import com.dfour.libraryplatform.entity.ReservationEntity;
import com.dfour.libraryplatform.exception.NotFoundException;
import com.dfour.libraryplatform.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservations;

    public Optional<ReservationEntity> findById(final Long id) {
        return reservations.findById(id);
    }

    public boolean isValid(final ReservationEntity reservation) {
        return Objects.isNull(reservation.getExpiredAt()) &&
                reservation.getExpiresAt().isAfter(OffsetDateTime.now(ZoneOffset.UTC)) &&
                Objects.equals(reservation.getCancelled(), false);
    }

    public Optional<ReservationEntity> getItemValidReservation(final Long itemId) {
        return reservations.findAllByItemId(itemId)
                .stream()
                .filter(this::isValid).findFirst();
    }

    public List<ReservationEntity> getUserValidReservation(final Long userId) {
        return reservations.findAllByUserId(userId)
                .stream()
                .filter(this::isValid).collect(Collectors.toList());
    }

    public void invalidateReservation(final ReservationEntity reservation) {
        reservation.setExpiredAt(OffsetDateTime.now(ZoneOffset.UTC));

        reservations.save(reservation);
    }

    public ReservationEntity cancelReservation(final Long reservationId) {
        final ReservationEntity reservation = reservations.findById(reservationId)
                .orElseThrow(NotFoundException::new);

        reservation.setCancelled(true);

        return reservations.save(reservation);
    }

    public ReservationEntity save(final ReservationEntity reservation) {
        return reservations.save(reservation);
    }

    public ReservationStatsDto reservationStats() {
        return ReservationStatsDto.builder()
                .currentReservations(reservations.countValidReservations())
                .build();
    }

    public List<ReservationEntity> findFiltered(final ReservationFilterDto filters) {
        return reservations.findFiltered(filters.getUserId(), filters.getItemId(), filters.getIsActive(),
                PageRequest.of(0, 100,
                        Sort.by(Sort.Direction.DESC, "id"))).getContent();
    }
}