package com.dfour.libraryplatform.domain.dto.requests;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExtendBorrowingRequestDto {

    private Long borrowingId;
    private Long extendDays;

}
