package com.dfour.libraryplatform.controller;

import com.dfour.libraryplatform.domain.dto.filters.BorrowingFilterDto;
import com.dfour.libraryplatform.domain.dto.requests.BorrowingRequestDto;
import com.dfour.libraryplatform.domain.dto.requests.ExtendBorrowingRequestDto;
import com.dfour.libraryplatform.domain.dto.stats.BorrowingStatsDto;
import com.dfour.libraryplatform.domain.dto.stats.ItemBorrowingStatsDto;
import com.dfour.libraryplatform.entity.BorrowingEntity;
import com.dfour.libraryplatform.exception.NotFoundException;
import com.dfour.libraryplatform.manager.BorrowingManager;
import com.dfour.libraryplatform.service.BorrowingService;
import com.dfour.libraryplatform.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.dfour.libraryplatform.security.AppAuthorization.EnsureUserId;
import static com.dfour.libraryplatform.security.AppAuthorization.EnsureUserLibrarian;

@RestController
@RequiredArgsConstructor
@RequestMapping("/borrowing")
public class BorrowingController {

    private final BorrowingService borrowingService;
    private final BorrowingManager borrowingManager;
    private final InventoryService inventoryService;

    @GetMapping
    List<BorrowingEntity> findBorrowings(
            @RequestParam(name = "userId", required = false) final Long userId,
            @RequestParam(name = "itemId", required = false) final Long itemId,
            @RequestParam(name = "isReturned", required = false) final Boolean isReturned
    ) {
        EnsureUserId(userId);

        return borrowingService.findFiltered(
                BorrowingFilterDto.builder()
                        .userId(userId)
                        .itemId(itemId)
                        .isReturned(isReturned)
                        .build()
        );
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BorrowingEntity borrow(
            @RequestBody final BorrowingRequestDto requestDto
    ) {
        EnsureUserLibrarian();

        inventoryService.findById(requestDto.getItemId())
                .orElseThrow(NotFoundException::new);
        return borrowingManager.borrowBook(requestDto);
    }

    @PostMapping("/{id}/returned")
    public BorrowingEntity markAsReturned(
            @PathVariable final Long id
    ) {
        EnsureUserLibrarian();

        return borrowingService.markAsReturned(id);
    }

    @PostMapping("/extend")
    public BorrowingEntity extendBorrowing(
            @RequestBody final ExtendBorrowingRequestDto requestDto
    ) {
        EnsureUserLibrarian();

        return borrowingService.extendBorrowing(requestDto);
    }

    @GetMapping("/stats/{itemId}")
    public ItemBorrowingStatsDto itemBorrowingStats(@PathVariable final Long itemId) {
        inventoryService.findById(itemId)
                .orElseThrow(NotFoundException::new);
        return borrowingService.getStats(itemId);
    }

    @GetMapping("/stats")
    public BorrowingStatsDto borrowingStats() {
        return borrowingService.getBorrowingStats();
    }

    @GetMapping("/user/{userId}")
    public List<BorrowingEntity> getBorrowingsByUserId(
            @PathVariable(name = "userId") final Long userId
    ) {
        return borrowingService.findByUserId(userId);
    }

}
