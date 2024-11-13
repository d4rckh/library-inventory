package com.dfour.libraryplatform.repository;

import com.dfour.libraryplatform.repository.entity.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.*;
import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {

    ArrayList<ReservationEntity> findAllByItemId(long itemId);
    ArrayList<ReservationEntity> findAllByUserId(long userId);

}
