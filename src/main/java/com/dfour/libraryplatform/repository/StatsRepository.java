package com.dfour.libraryplatform.repository;

import com.dfour.libraryplatform.entity.StatsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface StatsRepository extends JpaRepository<StatsEntity, Long> {
    Optional<StatsEntity> findByStatDate(LocalDate date);
}
