package com.dfour.libraryplatform.controller;

import com.dfour.libraryplatform.domain.dto.filters.ReservationFilterDto;
import com.dfour.libraryplatform.domain.dto.requests.ReservationRequestDto;
import com.dfour.libraryplatform.domain.dto.stats.ReservationStatsDto;
import com.dfour.libraryplatform.entity.ReservationEntity;
import com.dfour.libraryplatform.exception.NotFoundException;
import com.dfour.libraryplatform.manager.ReservationManager;
import com.dfour.libraryplatform.security.AppUserDetails;
import com.dfour.libraryplatform.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.dfour.libraryplatform.security.authentication.AppAuthentication.GetLoggedUserDetails;

@RestController
@RequestMapping("/reservation")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;
    private final ReservationManager reservationManager;

    @GetMapping
    List<ReservationEntity> findReservations(
            @RequestParam(name = "userId", required = false) final Long userId,
            @RequestParam(name = "isActive", required = false) final Boolean isActive,
            @RequestParam(name = "itemId", required = false) final Long itemId
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
    ReservationEntity reserve(@RequestBody final ReservationRequestDto reservationRequestDto) {
        final AppUserDetails appUserDetails = GetLoggedUserDetails();
        return reservationManager.reserveItem(
                ReservationRequestDto.builder()
                        .itemId(reservationRequestDto.getItemId())
                        .userId(appUserDetails.getEntity().getId())
                        .build()
        );
    }

    @PostMapping("/{id}/cancel")
    public ReservationEntity cancelReservation(
            @PathVariable final Long id
    ) {
        return reservationService.cancelReservation(id);
    }

    @GetMapping("/stats")
    ReservationStatsDto stats() {
        return reservationService.reservationStats();
    }

    @GetMapping("/item/{itemId}/valid")
    ReservationEntity getValidReservationsByItemId(@PathVariable final Long itemId) {
        return reservationService.getItemValidReservation(itemId)
                .orElseThrow(NotFoundException::new);
    }

    @GetMapping("/{id}")
    ReservationEntity getReservationById(@PathVariable final Long id) {
        return reservationService.findById(id)
                .orElseThrow(NotFoundException::new);
    }

}
