package com.dfour.libraryplatform.repository;

import com.dfour.libraryplatform.entity.BorrowingEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface BorrowingRepository extends JpaRepository<BorrowingEntity, Long> {

    @Query(value = "SELECT * FROM borrowings WHERE " +
            "borrowings.item_id = :itemId AND public.borrowings.returned_date IS NULL", nativeQuery = true)
    Optional<BorrowingEntity> getItemValidBorrowing(long itemId);

    long countByItemId(long itemId);

    Slice<BorrowingEntity> findByUserId(long userId, Pageable pageable);

    @Query(value = "SELECT COUNT(*) FROM borrowings WHERE " +
            "public.borrowings.returned_date IS NULL", nativeQuery = true)
    long countNotReturned();

    @Query(value = "SELECT COUNT(*) FROM borrowings WHERE " +
            "public.borrowings.returned_date IS NULL AND NOW() > public.borrowings.return_date", nativeQuery = true)
    long countLateBorrows();

    @Query(value = "SELECT * FROM borrowings WHERE (borrowings.user_id = :userId OR :userId IS NULL) AND " +
            "(borrowings.item_id = :itemId OR :itemId IS NULL) AND" +
            "(((borrowings.returned_date IS NOT NULL) = :isReturned) OR :isReturned IS NULL)", nativeQuery = true)
    Slice<BorrowingEntity> findFiltered(Long userId, Long itemId, Boolean isReturned, PageRequest pageRequest);

    @Query("SELECT COUNT(r) FROM BorrowingEntity r WHERE DATE(r.borrowDate) = :date")
    long countByBorrowingDate(LocalDate date);

    @Query("SELECT COUNT(r) FROM BorrowingEntity r WHERE DATE(r.returnedDate) = :date")
    long countByReturnDate(LocalDate date);

}
