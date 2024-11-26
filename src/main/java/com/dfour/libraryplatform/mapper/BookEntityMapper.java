package com.dfour.libraryplatform.mapper;

import com.dfour.libraryplatform.domain.dto.BookResponseDto;
import com.dfour.libraryplatform.domain.dto.InventoryResponseDto;
import com.dfour.libraryplatform.entity.BookEntity;
import com.dfour.libraryplatform.entity.InventoryEntity;
import com.dfour.libraryplatform.service.BookService;
import com.dfour.libraryplatform.service.BorrowingService;
import com.dfour.libraryplatform.service.InventoryService;
import com.dfour.libraryplatform.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookEntityMapper {

    private final InventoryService inventoryService;

    public BookResponseDto entityToDto(BookEntity entity) {
        return BookResponseDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .author(entity.getAuthor())
                .publisher(entity.getPublisher())
                .user(entity.getUser())
                .userId(entity.getUserId())
                .publishedDate(entity.getPublishedDate())
                .isbn(entity.getIsbn())
                .tags(entity.getTags())
                .totalItems(inventoryService.countByBookId(entity.getId()))
                .build();
    }

}
