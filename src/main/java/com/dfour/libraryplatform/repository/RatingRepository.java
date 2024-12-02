package com.dfour.libraryplatform.repository;

import com.dfour.libraryplatform.entity.RatingEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends JpaRepository<RatingEntity, Long> {

    @Query("SELECT r FROM RatingEntity r WHERE (:userId = r.userId OR :userId IS NULL) AND " +
            "(:bookId = r.bookId OR :bookId IS NULL)")
    Slice<RatingEntity> findFiltered(
            Long userId,
            Long bookId,
            Pageable pageable
    );

}
