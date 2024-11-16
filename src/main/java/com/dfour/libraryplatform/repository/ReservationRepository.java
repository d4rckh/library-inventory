package com.dfour.libraryplatform.repository;

import com.dfour.libraryplatform.entity.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {

    ArrayList<ReservationEntity> findAllByItemId(long itemId);

    ArrayList<ReservationEntity> findAllByUserId(long userId);

    @Query(value = "SELECT COUNT(*) FROM reservations WHERE reservations.expires_at > NOW() AND reservations.expired_at IS NULL", nativeQuery = true)
    long countValidReservations();
}
