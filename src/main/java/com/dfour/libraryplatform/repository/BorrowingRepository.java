package com.dfour.libraryplatform.repository;

import com.dfour.libraryplatform.repository.entity.BorrowingEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BorrowingRepository extends JpaRepository<BorrowingEntity, Long> {

    @Query(value = "SELECT * FROM borrowings WHERE " +
            "borrowings.item_id = :itemId AND public.borrowings.returned_date IS NULL", nativeQuery = true)
    Optional<BorrowingEntity> isBorrowed(long itemId);

    long countByItemId(long itemId);

    Slice<BorrowingEntity> findByUserId(long userId, Pageable pageable);
}
