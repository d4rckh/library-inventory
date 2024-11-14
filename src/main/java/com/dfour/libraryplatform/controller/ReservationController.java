package com.dfour.libraryplatform.controller;

import com.dfour.libraryplatform.domain.dto.ReservationRequestDto;
import com.dfour.libraryplatform.exception.NotFoundException;
import com.dfour.libraryplatform.manager.ReservationManager;
import com.dfour.libraryplatform.repository.entity.ReservationEntity;
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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ReservationEntity reserve(@RequestBody ReservationRequestDto reservationRequestDto) {
        return reservationManager.reserveItem(reservationRequestDto);
    }

    @GetMapping("/item/{itemId}")
    ArrayList<ReservationEntity> getReservationsByItemId(@PathVariable Long itemId) {
        return reservationService.findByItemId(itemId);
    }

    @GetMapping("/user/{userId}")
    ArrayList<ReservationEntity> getReservationsByUserId(@PathVariable Long userId) {
        return reservationService.findByUserId(userId);
    }

    @GetMapping("/{id}")
    ReservationEntity getReservationsById(@PathVariable Long id) {
        return reservationService.findById(id)
                .orElseThrow(NotFoundException::new);
    }

}
