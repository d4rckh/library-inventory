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
    Slice<InventoryEntity> findByBookIdAndDeletedFalse(Long bookId, Pageable pageable);

    @Query("SELECT i FROM InventoryEntity i WHERE " +
            "(i.bookId = :bookId OR :bookId IS NULL) AND " +
            "i.deleted IS FALSE")
    ArrayList<InventoryEntity> findFiltered(Long bookId);

    long countByBookIdAndDeletedFalse(Long bookId);
}
