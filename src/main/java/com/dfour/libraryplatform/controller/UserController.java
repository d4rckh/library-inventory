package com.dfour.libraryplatform.controller;

import com.dfour.libraryplatform.domain.dto.UserSignUpDto;
import com.dfour.libraryplatform.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    boolean signUp(@RequestBody UserSignUpDto userSignUpDto) {
        return userService.signUp(userSignUpDto);
    }

}