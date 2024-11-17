package com.dfour.libraryplatform.domain.dto.stats;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemBorrowingStatsDto {
    private final long times;
    private final boolean borrowed;
}
