package com.dfour.libraryplatform.domain.dto;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Data
@Builder
@RequiredArgsConstructor @Setter
public class ReservationRequestDto {
    private final Long userId;
    private final Long itemId;
}
