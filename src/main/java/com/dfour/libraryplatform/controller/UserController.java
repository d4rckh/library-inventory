package com.dfour.libraryplatform.controller;

import com.dfour.libraryplatform.domain.dto.filters.UserFilterDto;
import com.dfour.libraryplatform.domain.dto.requests.UserSignUpRequestDto;
import com.dfour.libraryplatform.domain.dto.stats.UserStatsDto;
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
    List<UserEntity> getUsers(
            @RequestParam(name = "email", required = false) String emailSearch
    ) {
        return userService.findFiltered(
                UserFilterDto.builder()
                        .emailSearch(emailSearch)
                        .build()
        );
    }

    @GetMapping("/stats")
    UserStatsDto userStats() {
        return userService.userStats();
    }

}
