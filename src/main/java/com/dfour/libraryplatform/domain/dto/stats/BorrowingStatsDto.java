package com.dfour.libraryplatform.domain.dto.stats;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BorrowingStatsDto {
    private Long currentBorrows;
    private Long currentLateBorrows;
}
