package com.dfour.libraryplatform.controller;

import com.dfour.libraryplatform.domain.dto.filters.RatingFilterDto;
import com.dfour.libraryplatform.domain.dto.requests.RatingCreateRequestDto;
import com.dfour.libraryplatform.entity.RatingEntity;
import com.dfour.libraryplatform.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.dfour.libraryplatform.security.AppAuthorization.EnsureUserId;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rating")
public class RatingController {

    private static final Logger log = LoggerFactory.getLogger(RatingController.class);
    private final RatingService ratingService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    RatingEntity createRating(@RequestBody final RatingCreateRequestDto ratingCreateRequestDto) {
        EnsureUserId(ratingCreateRequestDto.getUserId());

        return ratingService.createOrUpdateRating(
                ratingCreateRequestDto.getUserId(),
                ratingCreateRequestDto.getBookId(),
                ratingCreateRequestDto.getRating()
        );
    }

    @GetMapping
    List<RatingEntity> findRatings(
            @RequestParam(name = "userId", required = false) final Long userId,
            @RequestParam(name = "bookId", required = false) final Long bookId
    ) {
        return ratingService.find(
                RatingFilterDto.builder()
                        .bookId(bookId)
                        .userId(userId)
                        .build()
        );
    }
}
