package com.dfour.libraryplatform.domain.dto;

import com.dfour.libraryplatform.entity.TagEntity;
import com.dfour.libraryplatform.entity.UserEntity;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class BookResponseDto {
    private Long id;
    private String title;
    private String author;
    private String isbn;
    private String publisher;
    private Long year;
    private UserEntity user;
    private Long userId;
    private List<TagEntity> tags;

    private Long totalItems;
    private Long reservedItems;
    private Long borrowedItems;
}
