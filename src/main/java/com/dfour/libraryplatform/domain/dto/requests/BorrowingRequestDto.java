package com.dfour.libraryplatform.domain.dto.requests;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BorrowingRequestDto {

    private final Long userId;
    private final Long itemId;
    private final Long borrowDays;

}
