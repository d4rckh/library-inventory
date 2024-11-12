package com.dfour.libraryplatform.domain.dto;

import lombok.*;

@Data
@Builder
@RequiredArgsConstructor
public class UserSignUpDto {
    private final String email;
    private final String password;
    private final String firstName;
    private final String lastName;
}
