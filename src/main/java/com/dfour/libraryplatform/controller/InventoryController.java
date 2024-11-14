package com.dfour.libraryplatform.controller;

import com.dfour.libraryplatform.exception.NotFoundException;
import com.dfour.libraryplatform.entity.InventoryEntity;
import com.dfour.libraryplatform.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping
    private List<InventoryEntity> findAll(
            @RequestParam(name = "bookId", required = false) Long bookId
    ) {
        return inventoryService.findByBookId(bookId);
    }

    @GetMapping("/{itemId}")
    private InventoryEntity findById(
            @PathVariable(name = "itemId") Long itemId
    ) {
        return inventoryService.findById(itemId)
                .orElseThrow(NotFoundException::new);
    }

}
