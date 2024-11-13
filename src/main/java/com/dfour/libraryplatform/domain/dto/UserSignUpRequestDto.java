package com.dfour.libraryplatform.domain.dto;

import lombok.*;

@Data
@Builder
@RequiredArgsConstructor
public class UserSignUpRequestDto {
    private final String email;
    private final String password;
    private final String firstName;
    private final String lastName;
}
