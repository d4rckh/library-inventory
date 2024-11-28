package com.dfour.libraryplatform.mapper;

import com.dfour.libraryplatform.domain.dto.InventoryResponseDto;
import com.dfour.libraryplatform.entity.InventoryEntity;
import com.dfour.libraryplatform.service.BorrowingService;
import com.dfour.libraryplatform.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InventoryEntityMapper {

    private final BorrowingService borrowingService;
    private final ReservationService reservationService;

    public InventoryResponseDto entityToDto(final InventoryEntity entity) {
        return InventoryResponseDto.builder()
                .id(entity.getId())
                .book(entity.getBook())
                .user(entity.getUser())
                .reservation(reservationService.getItemValidReservation(entity.getId()).orElse(null))
                .borrowing(borrowingService.getItemValidBorrowing(entity.getId()).orElse(null))
                .stats(borrowingService.getStats(entity.getId()))
                .build();
    }

}
