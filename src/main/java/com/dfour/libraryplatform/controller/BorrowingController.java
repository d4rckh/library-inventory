package com.dfour.libraryplatform.controller;

import com.dfour.libraryplatform.domain.dto.BorrowingRequestDto;
import com.dfour.libraryplatform.domain.dto.BorrowingStatsDto;
import com.dfour.libraryplatform.exception.NotFoundException;
import com.dfour.libraryplatform.manager.BorrowingManager;
import com.dfour.libraryplatform.repository.entity.BorrowingEntity;
import com.dfour.libraryplatform.service.BorrowingService;
import com.dfour.libraryplatform.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/borrowing")
public class BorrowingController {

    private final BorrowingService borrowingService;
    private final BorrowingManager borrowingManager;
    private final InventoryService inventoryService;

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
    public BorrowingStatsDto borrowingStats(@PathVariable long itemId) {
        inventoryService.findById(itemId)
                .orElseThrow(NotFoundException::new);
        return borrowingService.getStats(itemId);
    }

    @GetMapping
    public List<BorrowingEntity> getBorrowings(
            @RequestParam(name = "userId") long userId
    ) {
        return borrowingService.findByUserId(userId);
    }

}
