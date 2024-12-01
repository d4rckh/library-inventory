package com.dfour.libraryplatform.manager;

import com.dfour.libraryplatform.domain.dto.requests.BorrowingRequestDto;
import com.dfour.libraryplatform.entity.BorrowingEntity;
import com.dfour.libraryplatform.entity.ReservationEntity;
import com.dfour.libraryplatform.exception.ItemIsBorrowedException;
import com.dfour.libraryplatform.service.BorrowingService;
import com.dfour.libraryplatform.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class BorrowingManager {
    private final ReservationService reservationService;
    private final BorrowingService borrowingService;

    public BorrowingEntity borrowBook(final BorrowingRequestDto borrowingRequest) {
        if (borrowingService.getItemValidBorrowing(borrowingRequest.getItemId()).isPresent())
            throw new ItemIsBorrowedException();

        final Optional<ReservationEntity> optionalReservationEntity =
                reservationService.getItemValidReservation(borrowingRequest.getItemId());

        if (optionalReservationEntity.isPresent()) {
            if (!Objects.equals(optionalReservationEntity.get().getUserId(),
                    borrowingRequest.getUserId())) {
                throw new ItemIsBorrowedException();
            }

            reservationService.invalidateReservation(optionalReservationEntity.get());
        }

        final BorrowingEntity borrowingEntity = new BorrowingEntity();

        borrowingEntity.setBorrowDate(OffsetDateTime.now(ZoneOffset.UTC));
        borrowingEntity.setReturnDate(borrowingEntity.getBorrowDate().plusDays(borrowingRequest.getBorrowDays()).plusHours(12));
        borrowingEntity.setUserId(borrowingRequest.getUserId());
        borrowingEntity.setItemId(borrowingRequest.getItemId());

        return borrowingService.save(borrowingEntity);
    }
}
