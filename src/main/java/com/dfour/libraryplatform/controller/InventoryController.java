package com.dfour.libraryplatform.controller;

import com.dfour.libraryplatform.domain.dto.filters.InventoryFilterDto;
import com.dfour.libraryplatform.domain.dto.InventoryResponseDto;
import com.dfour.libraryplatform.domain.dto.stats.InventoryStatsDto;
import com.dfour.libraryplatform.exception.NotFoundException;
import com.dfour.libraryplatform.entity.InventoryEntity;
import com.dfour.libraryplatform.mapper.InventoryEntityMapper;
import com.dfour.libraryplatform.security.AppUserDetails;
import com.dfour.libraryplatform.security.authentication.AppAuthentication;
import com.dfour.libraryplatform.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;
    private final InventoryEntityMapper inventoryEntityMapper;

    @GetMapping
    public List<InventoryResponseDto> findFiltered(
            @RequestParam(name="bookId", required = false) Long bookId
    ) {
        return inventoryService.findFiltered(
                InventoryFilterDto.builder()
                        .bookId(bookId)
                        .build()
        ).stream().map(inventoryEntityMapper::entityToDto).collect(Collectors.toList());
    }

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

    @PostMapping
    private InventoryEntity createInventoryAsUser(
            @RequestBody InventoryEntity inventoryEntity
    ) {
        AppUserDetails appUserDetails = AppAuthentication.GetLoggedUserDetails();
        return inventoryService.createInventoryAsUser(inventoryEntity, appUserDetails.getEntity());
    }

    @GetMapping("/stats")
    private InventoryStatsDto getInventoryStats() {
        return inventoryService.inventoryStats();
    }
}
