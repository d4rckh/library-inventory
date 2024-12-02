package com.dfour.libraryplatform.manager;

import com.dfour.libraryplatform.domain.dto.requests.ReservationRequestDto;
import com.dfour.libraryplatform.entity.BorrowingEntity;
import com.dfour.libraryplatform.entity.ReservationEntity;
import com.dfour.libraryplatform.exception.ItemIsBorrowedException;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReservationManagerTest {

    @Captor
    ArgumentCaptor<ReservationEntity> reservationEntityArgumentCaptor;
    @Mock
    private ReservationService reservationService;
    @Mock
    private BorrowingService borrowingService;
    @InjectMocks
    private ReservationManager reservationManager;

    @Test
    void reservationManagerShouldReserve() {
        // Given
        final long itemId = 1;
        final long userId = 1;

        final ReservationRequestDto reservationRequestDto =
                ReservationRequestDto.builder()
                        .itemId(itemId)
                        .userId(userId)
                        .build();

        // Item is not currently borrowed
        when(borrowingService.getItemValidBorrowing(itemId))
                .thenReturn(Optional.empty());

        // No other reservation for this item exists
        when(reservationService.getItemValidReservation(itemId))
                .thenReturn(Optional.empty());

        // When reserve item
        reservationManager.reserveItem(reservationRequestDto);

        // There should be a reservation
        verify(reservationService).save(reservationEntityArgumentCaptor.capture());
        final ReservationEntity reservationEntity = reservationEntityArgumentCaptor.getValue();

        // That has
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
        final long itemId = 1;
        final long userId = 1;
        final long userIdReserved = 1;

        final ReservationRequestDto reservationRequestDto =
                ReservationRequestDto.builder()
                        .itemId(itemId)
                        .userId(userId)
                        .build();

        // Item is not currently borrowed
        when(borrowingService.getItemValidBorrowing(itemId))
                .thenReturn(Optional.empty());

        when(reservationService.getItemValidReservation(itemId))
                .thenReturn(Optional.of(ReservationEntity.builder()
                        .itemId(itemId)
                        .userId(userIdReserved)
                        .createdAt(OffsetDateTime.now(ZoneOffset.UTC))
                        .expiresAt(OffsetDateTime.now(ZoneOffset.UTC).plusDays(2))
                        .expiredAt(null)
                        .build()));

        assertThrows(ItemIsReservedByYouException.class, () -> reservationManager.reserveItem(reservationRequestDto));
    }

    @Test
    void reservationManagerShouldNotReserveWhenItemReservedBySomeoneElse() {
        final long itemId = 1;
        final long userId = 1;
        final long userIdReserved = 2;

        final ReservationRequestDto reservationRequestDto =
                ReservationRequestDto.builder()
                        .itemId(itemId)
                        .userId(userId)
                        .build();

        // Item is not currently borrowed
        when(borrowingService.getItemValidBorrowing(itemId))
                .thenReturn(Optional.empty());

        when(reservationService.getItemValidReservation(itemId))
                .thenReturn(Optional.of(ReservationEntity.builder()
                        .itemId(itemId)
                        .userId(userIdReserved)
                        .createdAt(OffsetDateTime.now(ZoneOffset.UTC))
                        .expiresAt(OffsetDateTime.now(ZoneOffset.UTC).plusDays(2))
                        .expiredAt(null)
                        .build()));

        assertThrows(ItemIsReservedBySomeoneElseException.class, () -> reservationManager.reserveItem(reservationRequestDto));
    }

    @Test
    void reservationManagerShouldNotReserveWhenItemBorrowed() {
        final long itemId = 1;
        final long userId = 1;

        final ReservationRequestDto reservationRequestDto =
                ReservationRequestDto.builder()
                        .itemId(itemId)
                        .userId(userId)
                        .build();

        // Item is currently borrowed
        when(borrowingService.getItemValidBorrowing(itemId))
                .thenReturn(Optional.of(BorrowingEntity.builder()
                        .itemId(itemId)
                        .userId(userId)
                        .borrowDate(OffsetDateTime.now(ZoneOffset.UTC))
                        .returnDate(OffsetDateTime.now(ZoneOffset.UTC).plusDays(2))
                        .returnedDate(null)
                        .build()));

        assertThrows(ItemIsBorrowedException.class, () -> reservationManager.reserveItem(reservationRequestDto));
    }

}
