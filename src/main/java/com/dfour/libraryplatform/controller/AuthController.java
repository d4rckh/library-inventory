package com.dfour.libraryplatform.controller;

import com.dfour.libraryplatform.domain.dto.AccessInformationResponseDto;
import com.dfour.libraryplatform.domain.dto.UserInformationDto;
import com.dfour.libraryplatform.domain.dto.UserSignInRequestDto;
import com.dfour.libraryplatform.entity.UserEntity;
import com.dfour.libraryplatform.security.authentication.AppAuthentication;
import com.dfour.libraryplatform.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    @PostMapping
    AccessInformationResponseDto signIn(@RequestBody UserSignInRequestDto userSignInRequestDto) {
        return userService.signIn(userSignInRequestDto);
    }

    @GetMapping
    UserInformationDto userInformation() {
        UserEntity user = AppAuthentication.GetLoggedUserDetails().getEntity();

        return UserInformationDto.builder()
                    .id(user.getId())
                    .email(user.getEmail())
                    .build();
    }

}
