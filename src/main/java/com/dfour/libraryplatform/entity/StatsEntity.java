package com.dfour.libraryplatform.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@Builder
@Table(name = "stats")
@NoArgsConstructor
@AllArgsConstructor
public class StatsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate statDate;

    @Column(nullable = false)
    private Long dailyBorrowings;

    @Column(nullable = false)
    private Long dailyReturns;

    @Column(nullable = false)
    private Long dailyReservations;

    @Column(nullable = false)
    private Long dailyUsersRegistered;
}
