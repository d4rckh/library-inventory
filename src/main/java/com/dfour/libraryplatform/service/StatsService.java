package com.dfour.libraryplatform.service;

import com.dfour.libraryplatform.entity.StatsEntity;
import com.dfour.libraryplatform.repository.BorrowingRepository;
import com.dfour.libraryplatform.repository.ReservationRepository;
import com.dfour.libraryplatform.repository.StatsRepository;
import com.dfour.libraryplatform.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
@Slf4j
public class StatsService {

    private final StatsRepository statsRepository;
    private final BorrowingRepository borrowingRepository;
    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;

    public List<StatsEntity> getStats() {
        return statsRepository.findAll(
                PageRequest.of(0, 100,
                        Sort.by(Sort.Direction.ASC, "statDate"))
        ).getContent();
    }

    @PostConstruct
    public void runOnStartup() {
        log.info("Running updateDailyStats on application startup...");
        final LocalDate today = LocalDate.now(ZoneId.of("UTC"));

        IntStream.range(0, 31).forEachOrdered(n -> this.calculateStats(today.minusDays(n)));
    }

    private void calculateStats(final LocalDate date) {
        final StatsEntity stats = statsRepository.findByStatDate(date)
                .orElse(new StatsEntity());

        stats.setStatDate(date);
        stats.setDailyBorrowings(borrowingRepository.countByBorrowingDate(date));
        stats.setDailyReturns(borrowingRepository.countByReturnDate(date));
        stats.setDailyReservations(reservationRepository.countByReservationDate(date));
        stats.setDailyUsersRegistered(userRepository.countByCreationDate(date));

        statsRepository.save(stats);

        log.info("Successfully updated stats: {}", stats);
    }

    @Scheduled(cron = "0 0 * * * ?") // Hourly
    @Transactional
    public void updateDailyStats() {
        final LocalDate today = LocalDate.now(ZoneId.of("UTC"));

        this.calculateStats(today);
    }
}
