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
    private long id;

    @Column(nullable = false)
    private LocalDate statDate;

    @Column(nullable = false)
    private long dailyBorrowings;

    @Column(nullable = false)
    private long dailyReturns;

    @Column(nullable = false)
    private long dailyReservations;

    @Column(nullable = false)
    private long dailyUsersRegistered;
}
