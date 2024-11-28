package com.dfour.libraryplatform.domain.dto.stats;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReservationStatsDto {
    private Long currentReservations;
}
