package com.dfour.libraryplatform.repository;

import com.dfour.libraryplatform.entity.InventoryEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface InventoryRepository extends JpaRepository<InventoryEntity, Long> {

    Slice<InventoryEntity> findByBookId(long bookId, Pageable pageable);

    @Query(value = "SELECT * FROM inventory WHERE (inventory.book_id = :bookId OR :bookId IS NULL)", nativeQuery = true)
    ArrayList<InventoryEntity> findFiltered(Long bookId);

}
