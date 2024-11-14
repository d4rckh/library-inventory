package com.dfour.libraryplatform.manager;

import com.dfour.libraryplatform.domain.dto.ReservationRequestDto;
import com.dfour.libraryplatform.exception.ItemIsBorrowed;
import com.dfour.libraryplatform.exception.ItemIsReservedBySomeoneElseException;
import com.dfour.libraryplatform.exception.ItemIsReservedByYouException;
import com.dfour.libraryplatform.entity.ReservationEntity;
import com.dfour.libraryplatform.service.BorrowingService;
import com.dfour.libraryplatform.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class ReservationManager {
    private final ReservationService reservationService;
    private final BorrowingService borrowingService;

    public ReservationEntity reserveItem(ReservationRequestDto reservationRequestDto) {
        Optional<ReservationEntity> optionalReservationEntity = reservationService.findById(reservationRequestDto.getItemId());

        if (borrowingService.isItemBorrowed(reservationRequestDto.getItemId()).isPresent()) {
            throw new ItemIsBorrowed();
        }

        if (optionalReservationEntity.isPresent()) {
            if (optionalReservationEntity.get().getUserId() == reservationRequestDto.getUserId()) {
                throw new ItemIsReservedByYouException();
            } else {
                throw new ItemIsReservedBySomeoneElseException();
            }
        }

        ReservationEntity reservation = ReservationEntity.builder()
                .expiresAt(OffsetDateTime.now(ZoneOffset.UTC).plusDays(2))
                .expiredAt(null)
                .userId(reservationRequestDto.getUserId())
                .itemId(reservationRequestDto.getItemId())
                .createdAt(OffsetDateTime.now(ZoneOffset.UTC))
                .build();

        return reservationService.save(reservation);
    }
}
