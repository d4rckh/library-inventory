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

    @GetMapping("/book/{bookId}")
    private List<InventoryEntity> findByBookId(
            @PathVariable(name = "bookId") Long bookId
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
