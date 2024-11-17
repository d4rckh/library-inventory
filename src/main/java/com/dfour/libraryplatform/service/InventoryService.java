package com.dfour.libraryplatform.service;

import com.dfour.libraryplatform.domain.dto.filters.InventoryFilterDto;
import com.dfour.libraryplatform.domain.dto.stats.InventoryStatsDto;
import com.dfour.libraryplatform.entity.UserEntity;
import com.dfour.libraryplatform.repository.InventoryRepository;
import com.dfour.libraryplatform.entity.InventoryEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventory;
    private final BorrowingService borrowingService;
    private final ReservationService reservationService;

    public List<InventoryEntity> findAll() {
        return inventory.findAll(
                PageRequest.of(0, 100,
                        Sort.by(Sort.Direction.DESC, "id"))
        ).getContent();
    }

    public List<InventoryEntity> findFiltered(InventoryFilterDto filter) {
        return inventory.findFiltered(filter.getBookId());
    }

    public List<InventoryEntity> findByBookId(long bookId) {
        return inventory.findByBookId(bookId,
                PageRequest.of(0, 100,
                        Sort.by(Sort.Direction.DESC, "id"))).getContent();
    }

    public Optional<InventoryEntity> findById(Long id) {
        return inventory.findById(id);
    }

    public InventoryEntity createInventoryAsUser(InventoryEntity inventoryEntity, UserEntity userEntity) {
        inventoryEntity.setUser(userEntity);
        inventoryEntity.setUserId(userEntity.getId());
        return inventory.save(inventoryEntity);
    }

    public InventoryStatsDto inventoryStats() {
        return InventoryStatsDto.builder()
                .uniqueItems(inventory.count())
                .build();
    }
}
