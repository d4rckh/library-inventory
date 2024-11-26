package com.dfour.libraryplatform.repository;

import com.dfour.libraryplatform.entity.ReservationEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {

    ArrayList<ReservationEntity> findAllByItemId(long itemId);

    ArrayList<ReservationEntity> findAllByUserId(long userId);

    @Query(value = "SELECT COUNT(*) FROM reservations WHERE reservations.expires_at > NOW() AND reservations.expired_at IS NULL AND reservations.cancelled = false", nativeQuery = true)
    long countValidReservations();

    @Query(value = "SELECT * FROM reservations WHERE (reservations.user_id = :userId OR :userId IS NULL) AND " +
            "(reservations.item_id = :itemId OR :itemId IS NULL) AND" +
            "(reservations.expires_at > NOW() AND reservations.expired_at IS NULL AND cancelled = false) = :isActive OR :isActive IS NULL", nativeQuery = true)
    Slice<ReservationEntity> findFiltered(Long userId, Long itemId, Boolean isActive, PageRequest pageRequest);
}
