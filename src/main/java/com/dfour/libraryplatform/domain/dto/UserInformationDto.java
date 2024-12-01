package com.dfour.libraryplatform.domain.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserInformationDto {

    private final String email;
    private final Long id;
    private final Boolean isLibrarian;

}
