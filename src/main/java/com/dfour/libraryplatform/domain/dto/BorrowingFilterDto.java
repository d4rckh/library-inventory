package com.dfour.libraryplatform.domain.dto;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class BorrowingFilterDto {
    private Long userId;
    private Long itemId;
    private Boolean isReturned;
}
