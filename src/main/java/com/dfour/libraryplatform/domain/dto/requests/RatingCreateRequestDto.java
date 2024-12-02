package com.dfour.libraryplatform.domain.dto.requests;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RatingCreateRequestDto {

    private final Long userId;
    private final Long bookId;
    private final Integer rating;

}
