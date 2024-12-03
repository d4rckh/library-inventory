package com.dfour.libraryplatform.controller;

import com.dfour.libraryplatform.domain.dto.BookResponseDto;
import com.dfour.libraryplatform.domain.dto.filters.BookFilterDto;
import com.dfour.libraryplatform.domain.dto.stats.BookStatsDto;
import com.dfour.libraryplatform.entity.BookEntity;
import com.dfour.libraryplatform.exception.NotFoundException;
import com.dfour.libraryplatform.mapper.BookEntityMapper;
import com.dfour.libraryplatform.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static com.dfour.libraryplatform.security.AppAuthentication.GetLoggedUserDetails;
import static com.dfour.libraryplatform.security.AppAuthorization.EnsureUserLibrarian;

@Slf4j
@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final BookEntityMapper bookEntityMapper;

    @GetMapping
    private List<BookResponseDto> findFiltered(
            @RequestParam(name = "titleSearch", required = false) final String titleSearch
    ) {
        return bookService.findFiltered(
                        BookFilterDto.builder()
                                .titleSearch(titleSearch)
                                .build()
                ).stream()
                .map(bookEntityMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @PatchMapping("/{bookId}")
    BookResponseDto patch(
            @PathVariable final Long bookId,
            @RequestBody final BookEntity incompleteBookEntity
    ) {
        EnsureUserLibrarian();

        return bookEntityMapper.entityToDto(bookService.patch(bookId, incompleteBookEntity));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{bookId}")
    void delete(
            @PathVariable final Long bookId
    ) {
        EnsureUserLibrarian();

        bookService.deleteById(bookId);
    }

    @GetMapping("/{bookId}")
    BookResponseDto getById(
            @PathVariable final Long bookId
    ) {
        return bookEntityMapper.entityToDto(bookService.findById(bookId)
                .orElseThrow(NotFoundException::new));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    BookResponseDto create(@RequestBody final BookEntity bookEntity) {
        EnsureUserLibrarian();

        return bookEntityMapper.entityToDto(
                bookService.createAsUser(
                        bookEntity,
                        GetLoggedUserDetails().getEntity()
                )
        );
    }

    @GetMapping("/stats")
    BookStatsDto bookStats() {
        return bookService.bookStats();
    }

}
