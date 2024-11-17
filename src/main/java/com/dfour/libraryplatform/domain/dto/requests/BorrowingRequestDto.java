package com.dfour.libraryplatform.domain.dto.requests;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BorrowingRequestDto {

    private final long userId;
    private final long itemId;
    private final long borrowDays;

}
