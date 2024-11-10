package com.dfour.libraryplatform.repository;

import com.dfour.libraryplatform.repository.entity.BookEntity;
import com.dfour.libraryplatform.repository.entity.InventoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<InventoryEntity, Long> {

    @Query(value = "SELECT * FROM inventory WHERE " +
            "(inventory.book_id = :bookId OR :bookId IS NULL)", nativeQuery = true)
    Page<InventoryEntity> findByFilter(long bookId, Pageable pageable);

}
