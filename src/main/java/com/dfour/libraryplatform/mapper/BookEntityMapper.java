package com.dfour.libraryplatform.mapper;

import com.dfour.libraryplatform.domain.dto.BookResponseDto;
import com.dfour.libraryplatform.entity.BookEntity;
import com.dfour.libraryplatform.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookEntityMapper {

    private final InventoryService inventoryService;

    public BookResponseDto entityToDto(final BookEntity entity) {
        return BookResponseDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .author(entity.getAuthor())
                .publisher(entity.getPublisher())
                .user(entity.getUser())
                .userId(entity.getUserId())
                .year(entity.getYear())
                .isbn(entity.getIsbn())
                .tags(entity.getTags())
                .totalItems(inventoryService.countByBookId(entity.getId()))
                .build();
    }

}
