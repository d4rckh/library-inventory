package com.dfour.libraryplatform.controller;

import com.dfour.libraryplatform.repository.entity.InventoryEntity;
import com.dfour.libraryplatform.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
        return inventoryService.findByFilter(bookId);
    }

}
