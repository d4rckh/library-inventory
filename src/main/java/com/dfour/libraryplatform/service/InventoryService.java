package com.dfour.libraryplatform.service;

import com.dfour.libraryplatform.domain.dto.InventoryFilterDto;
import com.dfour.libraryplatform.domain.dto.InventoryStatsDto;
import com.dfour.libraryplatform.entity.UserEntity;
import com.dfour.libraryplatform.repository.InventoryRepository;
import com.dfour.libraryplatform.entity.InventoryEntity;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private static final Logger log = LoggerFactory.getLogger(InventoryService.class);
    private final InventoryRepository inventory;

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
