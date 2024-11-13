package com.dfour.libraryplatform.domain.dto;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
public class ReservationRequestDto {
    private final Long userId;
    private final Long itemId;
}
