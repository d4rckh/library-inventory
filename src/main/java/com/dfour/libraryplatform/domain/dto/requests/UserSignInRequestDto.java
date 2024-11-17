package com.dfour.libraryplatform.domain.dto.requests;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
public class UserSignInRequestDto {
    private final String email;
    private final String password;
}
