package com.dfour.libraryplatform.controller;

import com.dfour.libraryplatform.domain.dto.InventoryResponseDto;
import com.dfour.libraryplatform.domain.dto.filters.InventoryFilterDto;
import com.dfour.libraryplatform.domain.dto.stats.InventoryStatsDto;
import com.dfour.libraryplatform.entity.InventoryEntity;
import com.dfour.libraryplatform.exception.NotFoundException;
import com.dfour.libraryplatform.mapper.InventoryEntityMapper;
import com.dfour.libraryplatform.security.AppUserDetails;
import com.dfour.libraryplatform.security.AppAuthentication;
import com.dfour.libraryplatform.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static com.dfour.libraryplatform.security.AppAuthorization.EnsureUserLibrarian;

@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;
    private final InventoryEntityMapper inventoryEntityMapper;

    @GetMapping
    public List<InventoryResponseDto> findFiltered(
            @RequestParam(name = "bookId", required = false) final Long bookId
    ) {
        return inventoryService.findFiltered(
                InventoryFilterDto.builder()
                        .bookId(bookId)
                        .build()
        ).stream().map(inventoryEntityMapper::entityToDto).collect(Collectors.toList());
    }

    @GetMapping("/book/{bookId}")
    private List<InventoryEntity> findByBookId(
            @PathVariable(name = "bookId") final Long bookId
    ) {
        return inventoryService.findByBookId(bookId);
    }

    @GetMapping("/{itemId}")
    private InventoryEntity findById(
            @PathVariable(name = "itemId") final Long itemId
    ) {
        return inventoryService.findById(itemId)
                .orElseThrow(NotFoundException::new);
    }

    @DeleteMapping("/{itemId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void delete(
            @PathVariable(name = "itemId") final Long itemId
    ) {
        EnsureUserLibrarian();
        inventoryService.deleteById(itemId);
    }

    @PostMapping
    private InventoryEntity createInventoryAsUser(
            @RequestBody final InventoryEntity inventoryEntity
    ) {
        final AppUserDetails appUserDetails = AppAuthentication.GetLoggedUserDetails();
        EnsureUserLibrarian();
        return inventoryService.createInventoryAsUser(inventoryEntity, appUserDetails.getEntity());
    }

    @GetMapping("/stats")
    private InventoryStatsDto getInventoryStats() {
        return inventoryService.inventoryStats();
    }
}
