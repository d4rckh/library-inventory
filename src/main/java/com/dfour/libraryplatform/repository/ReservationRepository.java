package com.dfour.libraryplatform.repository;

import com.dfour.libraryplatform.entity.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {

    ArrayList<ReservationEntity> findAllByItemId(long itemId);
    ArrayList<ReservationEntity> findAllByUserId(long userId);

}
