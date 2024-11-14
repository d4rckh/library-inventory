package com.dfour.libraryplatform.manager;

import com.dfour.libraryplatform.domain.dto.ReservationRequestDto;
import com.dfour.libraryplatform.entity.BorrowingEntity;
import com.dfour.libraryplatform.entity.ReservationEntity;
import com.dfour.libraryplatform.exception.ItemIsBorrowed;
import com.dfour.libraryplatform.exception.ItemIsReservedBySomeoneElseException;
import com.dfour.libraryplatform.exception.ItemIsReservedByYouException;
import com.dfour.libraryplatform.service.BorrowingService;
import com.dfour.libraryplatform.service.ReservationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReservationManagerTest {

    @Mock
    private ReservationService reservationService;

    @Mock
    private BorrowingService borrowingService;

    @InjectMocks
    private ReservationManager reservationManager;

    @Captor
    ArgumentCaptor<ReservationEntity> reservationEntityArgumentCaptor;

    @Test
    void reservationManagerShouldReserve() {
        long itemId = 1;
        long userId = 1;

        ReservationRequestDto reservationRequestDto =
                ReservationRequestDto.builder()
                        .itemId(itemId)
                        .userId(userId)
                        .build();

        // Item is not currently borrowed
        when(borrowingService.isItemBorrowed(itemId))
                .thenReturn(Optional.empty());

        // No other reservation for this item exists
        when(reservationService.getItemValidReservation(itemId))
                .thenReturn(Optional.empty());

        reservationManager.reserveItem(reservationRequestDto);

        verify(reservationService).save(reservationEntityArgumentCaptor.capture());
        ReservationEntity reservationEntity = reservationEntityArgumentCaptor.getValue();

        assertEquals(itemId, reservationEntity.getItemId());
        assertEquals(userId, reservationEntity.getUserId());
        assertTrue(
                reservationEntity.getExpiresAt()
                        .isAfter(OffsetDateTime.now(ZoneOffset.UTC)
                                .plusDays(2).minusMinutes(1))
        );
    }

    @Test
    void reservationManagerShouldNotReserveWhenItemReserved() {
        long itemId = 1;
        long userId = 1;
        long userIdReserved = 1;

        ReservationRequestDto reservationRequestDto =
                ReservationRequestDto.builder()
                        .itemId(itemId)
                        .userId(userId)
                        .build();

        // Item is not currently borrowed
        when(borrowingService.isItemBorrowed(itemId))
                .thenReturn(Optional.empty());

        when(reservationService.getItemValidReservation(itemId))
                .thenReturn(Optional.of(ReservationEntity.builder()
                        .itemId(itemId)
                        .userId(userIdReserved)
                        .createdAt(OffsetDateTime.now(ZoneOffset.UTC))
                        .expiresAt(OffsetDateTime.now(ZoneOffset.UTC).plusDays(2))
                        .expiredAt(null)
                        .build()));

        assertThrows(ItemIsReservedByYouException.class, () -> {
            reservationManager.reserveItem(reservationRequestDto);
        });
    }

    @Test
    void reservationManagerShouldNotReserveWhenItemReservedBySomeoneElse() {
        long itemId = 1;
        long userId = 1;
        long userIdReserved = 2;

        ReservationRequestDto reservationRequestDto =
                ReservationRequestDto.builder()
                        .itemId(itemId)
                        .userId(userId)
                        .build();

        // Item is not currently borrowed
        when(borrowingService.isItemBorrowed(itemId))
                .thenReturn(Optional.empty());

        when(reservationService.getItemValidReservation(itemId))
                .thenReturn(Optional.of(ReservationEntity.builder()
                        .itemId(itemId)
                        .userId(userIdReserved)
                        .createdAt(OffsetDateTime.now(ZoneOffset.UTC))
                        .expiresAt(OffsetDateTime.now(ZoneOffset.UTC).plusDays(2))
                        .expiredAt(null)
                        .build()));

        assertThrows(ItemIsReservedBySomeoneElseException.class, () -> {
            reservationManager.reserveItem(reservationRequestDto);
        });
    }

    @Test
    void reservationManagerShouldNotReserveWhenItemBorrowed() {
        long itemId = 1;
        long userId = 1;

        ReservationRequestDto reservationRequestDto =
                ReservationRequestDto.builder()
                        .itemId(itemId)
                        .userId(userId)
                        .build();

        // Item is currently borrowed
        when(borrowingService.isItemBorrowed(itemId))
                .thenReturn(Optional.of(BorrowingEntity.builder()
                        .itemId(itemId)
                        .userId(userId)
                        .borrowDate(OffsetDateTime.now(ZoneOffset.UTC))
                        .returnDate(OffsetDateTime.now(ZoneOffset.UTC).plusDays(2))
                        .returnedDate(null)
                        .build()));

        assertThrows(ItemIsBorrowed.class, () -> {
            reservationManager.reserveItem(reservationRequestDto);
        });
    }

}
