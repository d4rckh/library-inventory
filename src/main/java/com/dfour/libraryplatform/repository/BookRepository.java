package com.dfour.libraryplatform.repository;

import com.dfour.libraryplatform.repository.entity.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {

    @Query("SELECT b FROM BookEntity b " +
            "JOIN b.tags t " +
            "GROUP BY b.id " +
            "HAVING (:isTagsEmpty = TRUE OR COUNT(DISTINCT CASE WHEN t.name IN :tags THEN t.id END) = :tagCount)")
    Page<BookEntity> findAll(@Param("tags") List<String> tags,
                             @Param("isTagsEmpty") boolean isTagsEmpty,
                             @Param("tagCount") long tagCount, Pageable pageable);

}
