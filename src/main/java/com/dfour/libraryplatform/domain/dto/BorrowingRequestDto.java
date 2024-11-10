package com.dfour.libraryplatform.domain.dto;

import lombok.Data;

@Data
public class BorrowingRequestDto {

    private final long userId;
    private final long itemId;

}
