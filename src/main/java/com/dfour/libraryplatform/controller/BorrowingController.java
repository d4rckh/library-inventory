package com.dfour.libraryplatform.controller;

import com.dfour.libraryplatform.domain.dto.filters.BorrowingFilterDto;
import com.dfour.libraryplatform.domain.dto.requests.BorrowingRequestDto;
import com.dfour.libraryplatform.domain.dto.stats.BorrowingStatsDto;
import com.dfour.libraryplatform.domain.dto.stats.ItemBorrowingStatsDto;
import com.dfour.libraryplatform.exception.NotFoundException;
import com.dfour.libraryplatform.entity.BorrowingEntity;
import com.dfour.libraryplatform.manager.BorrowingManager;
import com.dfour.libraryplatform.service.BorrowingService;
import com.dfour.libraryplatform.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/borrowing")
public class BorrowingController {

    private final BorrowingService borrowingService;
    private final BorrowingManager borrowingManager;
    private final InventoryService inventoryService;

    @GetMapping
    ArrayList<BorrowingEntity> findBorrowings(
            @RequestParam(name = "userId", required = false) Long userId,
            @RequestParam(name = "itemId", required = false) Long itemId,
            @RequestParam(name = "isReturned", required = false) Boolean isReturned
    ) {
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
            @RequestBody BorrowingRequestDto requestDto
    ) {
        inventoryService.findById(requestDto.getItemId())
                .orElseThrow(NotFoundException::new);
        return borrowingManager.borrowBook(requestDto);
    }

    @GetMapping("/stats/{itemId}")
    public ItemBorrowingStatsDto itemBorrowingStats(@PathVariable long itemId) {
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
            @PathVariable(name = "userId") long userId
    ) {
        return borrowingService.findByUserId(userId);
    }

}
