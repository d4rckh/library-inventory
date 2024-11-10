package com.dfour.libraryplatform.domain.dto;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class BorrowingStatsDto {
    private final long times;
    private final boolean borrowed;
}
