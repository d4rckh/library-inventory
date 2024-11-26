package com.dfour.libraryplatform.domain.dto;

import com.dfour.libraryplatform.domain.dto.stats.ItemBorrowingStatsDto;
import com.dfour.libraryplatform.entity.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.List;

@Data
@Builder
public class BookResponseDto {
    private long id;
    private String title;
    private String author;
    private String isbn;
    private String publisher;
    private OffsetDateTime publishedDate;
    private UserEntity user;
    private long userId;
    private List<TagEntity> tags;

    private long totalItems;
    private long reservedItems;
    private long borrowedItems;
}
