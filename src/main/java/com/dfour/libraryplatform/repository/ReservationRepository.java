package com.dfour.libraryplatform.repository;

import com.dfour.libraryplatform.entity.ReservationEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {

    ArrayList<ReservationEntity> findAllByItemId(Long itemId);

    ArrayList<ReservationEntity> findAllByUserId(Long userId);

    @Query("SELECT COUNT(r) FROM ReservationEntity r WHERE " +
            "r.expiresAt > CURRENT TIMESTAMP AND " +
            "r.expiredAt IS NULL AND " +
            "r.cancelled = false")
    long countValidReservations();

    @Query("SELECT r FROM ReservationEntity r WHERE " +
            "(:userId IS NULL OR r.userId = :userId) AND " +
            "(:itemId IS NULL OR r.itemId = :itemId) AND " +
            "(:isActive IS NULL OR " +
            " (:isActive = true AND r.expiresAt > CURRENT_TIMESTAMP AND r.expiredAt IS NULL AND r.cancelled = false) OR " +
            " (:isActive = false AND (r.expiresAt <= CURRENT_TIMESTAMP OR r.expiredAt IS NOT NULL OR r.cancelled = true)))")
    Slice<ReservationEntity> findFiltered(Long userId, Long itemId, Boolean isActive, PageRequest pageRequest);

    @Query("SELECT COUNT(r) FROM ReservationEntity r WHERE DATE(r.createdAt) = :date")
    long countByReservationDate(LocalDate date);
}
