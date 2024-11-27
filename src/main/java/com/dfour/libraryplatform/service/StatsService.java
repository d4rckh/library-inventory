package com.dfour.libraryplatform.service;

import com.dfour.libraryplatform.entity.StatsEntity;
import com.dfour.libraryplatform.repository.BorrowingRepository;
import com.dfour.libraryplatform.repository.ReservationRepository;
import com.dfour.libraryplatform.repository.StatsRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.stream.IntStream;
import java.util.List;

@Service @RequiredArgsConstructor @Slf4j
public class StatsService {

    private final StatsRepository statsRepository;
    private final BorrowingRepository borrowingRepository;
    private final ReservationRepository reservationRepository;

    public List<StatsEntity> getStats() {
        return statsRepository.findAll(
                PageRequest.of(0, 100,
                        Sort.by(Sort.Direction.ASC, "statDate"))
        ).getContent();
    }

    @PostConstruct
    public void runOnStartup() {
        log.info("Running updateDailyStats on application startup...");

        LocalDate today = LocalDate.now(ZoneId.of("UTC"));

        IntStream.range(0, 31).forEachOrdered(n -> this.calculateStats(today.minusDays(n)));
    }

    private void calculateStats(LocalDate date) {
        long borrowings = borrowingRepository.countByBorrowingDate(date);
        long returns = borrowingRepository.countByReturnDate(date);
        long reservations = reservationRepository.countByReservationDate(date);

        StatsEntity stats = statsRepository.findByStatDate(date)
                .orElse(new StatsEntity());

        stats.setStatDate(date);
        stats.setDailyBorrowings(borrowings);
        stats.setDailyReturns(returns);
        stats.setDailyReservations(reservations);

        statsRepository.save(stats);

        log.info("Successfully updated stats: {}", stats);
    }

    @Scheduled(cron = "0 0 * * * ?") // Hourly
    @Transactional
    public void updateDailyStats() {
        LocalDate today = LocalDate.now(ZoneId.of("UTC"));

        this.calculateStats(today);

    }
}
