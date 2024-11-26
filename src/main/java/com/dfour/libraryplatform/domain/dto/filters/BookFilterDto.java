package com.dfour.libraryplatform.domain.dto.filters;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookFilterDto {

    private final String titleSearch;

}
