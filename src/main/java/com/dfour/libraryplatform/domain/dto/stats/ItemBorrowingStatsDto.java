package com.dfour.libraryplatform.domain.dto.stats;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemBorrowingStatsDto {
    private final Long times;
    private final Boolean borrowed;
}
