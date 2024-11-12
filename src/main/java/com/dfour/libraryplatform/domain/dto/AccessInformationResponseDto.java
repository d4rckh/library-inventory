package com.dfour.libraryplatform.domain.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data @RequiredArgsConstructor
public class AccessInformationResponseDto {
    private final String jwtToken;
}
