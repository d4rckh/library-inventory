package com.dfour.libraryplatform.controller;

import com.dfour.libraryplatform.domain.dto.UserSignUpRequestDto;
import com.dfour.libraryplatform.domain.dto.UserStatsDto;
import com.dfour.libraryplatform.entity.UserEntity;
import com.dfour.libraryplatform.exception.NotFoundException;
import com.dfour.libraryplatform.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Boolean signUp(@RequestBody UserSignUpRequestDto userSignUpRequestDto) {
        return userService.signUp(userSignUpRequestDto);
    }

    @GetMapping("/{userId}")
    UserEntity getUser(@PathVariable Long userId) {
        return userService.findById(userId)
                .orElseThrow(NotFoundException::new);
    }

    @GetMapping
    List<UserEntity> getUsers() {
        return userService.findAll();
    }

    @GetMapping("/stats")
    UserStatsDto userStats() {
        return userService.userStats();
    }

}
