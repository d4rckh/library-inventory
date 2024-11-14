package com.dfour.libraryplatform.repository;

import com.dfour.libraryplatform.repository.entity.InventoryEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<InventoryEntity, Long> {

    Slice<InventoryEntity> findByBookId(long bookId, Pageable pageable);

}
