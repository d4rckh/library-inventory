package com.dfour.libraryplatform.repository;

import com.dfour.libraryplatform.entity.BookEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {

    @Query(value = "SELECT b FROM BookEntity b WHERE (LOWER(b.title) LIKE %:titleSearch% OR :titleSearch IS NULL)")
    Slice<BookEntity> findFiltered(String titleSearch, Pageable pageable);

}
