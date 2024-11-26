package com.dfour.libraryplatform.domain.dto;

import com.dfour.libraryplatform.domain.dto.stats.ItemBorrowingStatsDto;
import com.dfour.libraryplatform.entity.BookEntity;
import com.dfour.libraryplatform.entity.BorrowingEntity;
import com.dfour.libraryplatform.entity.ReservationEntity;
import com.dfour.libraryplatform.entity.UserEntity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InventoryResponseDto {
    private long id;

    private UserEntity user;
    private BookEntity book;

    private ItemBorrowingStatsDto stats;

    private BorrowingEntity borrowing;
    private ReservationEntity reservation;
}
