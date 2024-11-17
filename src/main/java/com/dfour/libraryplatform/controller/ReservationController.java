package com.dfour.libraryplatform.controller;

import com.dfour.libraryplatform.domain.dto.filters.ReservationFilterDto;
import com.dfour.libraryplatform.domain.dto.requests.ReservationRequestDto;
import com.dfour.libraryplatform.domain.dto.stats.ReservationStatsDto;
import com.dfour.libraryplatform.entity.ReservationEntity;
import com.dfour.libraryplatform.exception.NotFoundException;
import com.dfour.libraryplatform.manager.ReservationManager;
import com.dfour.libraryplatform.security.AppUserDetails;
import com.dfour.libraryplatform.security.authentication.AppAuthentication;
import com.dfour.libraryplatform.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/reservation")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;
    private final ReservationManager reservationManager;

    @GetMapping
    ArrayList<ReservationEntity> findReservations(
            @RequestParam(name = "userId", required = false) Long userId,
            @RequestParam(name = "isActive", required = false) Boolean isActive,
            @RequestParam(name = "itemId", required = false) Long itemId
            ) {
        return reservationService.findFiltered(
                ReservationFilterDto.builder()
                        .userId(userId)
                        .isActive(isActive)
                        .itemId(itemId)
                        .build()
        );
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ReservationEntity reserve(@RequestBody ReservationRequestDto reservationRequestDto) {
        AppUserDetails appUserDetails = AppAuthentication.GetLoggedUserDetails();
        return reservationManager.reserveItem(
                ReservationRequestDto.builder()
                        .itemId(reservationRequestDto.getItemId())
                        .userId(appUserDetails.getEntity().getId())
                        .build()
        );
    }

    @GetMapping("/item/{itemId}")
    ArrayList<ReservationEntity> getReservationsByItemId(@PathVariable Long itemId) {
        return reservationService.findByItemId(itemId);
    }

    @GetMapping("/stats")
    ReservationStatsDto stats() {
        return reservationService.reservationStats();
    }

    @GetMapping("/item/{itemId}/valid")
    ReservationEntity getValidReservationsByItemId(@PathVariable Long itemId) {
        return reservationService.getItemValidReservation(itemId)
                .orElseThrow(NotFoundException::new);
    }

    @GetMapping("/user/{userId}")
    ArrayList<ReservationEntity> getReservationsByUserId(@PathVariable Long userId) {
        return reservationService.findByUserId(userId);
    }

    @GetMapping("/{id}")
    ReservationEntity getReservationById(@PathVariable Long id) {
        return reservationService.findById(id)
                .orElseThrow(NotFoundException::new);
    }

}
