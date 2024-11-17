package com.dfour.libraryplatform.domain.dto.stats;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BorrowingStatsDto {
    private long currentBorrows;
    private long currentLateBorrows;
}
