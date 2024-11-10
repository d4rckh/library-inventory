package com.dfour.libraryplatform.controller;

import com.dfour.libraryplatform.repository.BookRepository;
import com.dfour.libraryplatform.repository.entity.BookEntity;
import com.dfour.libraryplatform.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    private List<BookEntity> findAll(
            @RequestParam(required = false) List<String> tags
    ) {
        return bookService.findAll(Objects.isNull(tags) ? new ArrayList<>() : tags);
    }

    @PatchMapping("/{bookId}")
    BookEntity patch(
            @PathVariable Long bookId,
            @RequestBody BookEntity incompleteBookEntity
    ) {
        return bookService.patch(bookId, incompleteBookEntity);
    }

    @PostMapping
    BookEntity create(@RequestBody BookEntity bookEntity) {
        return bookService.create(bookEntity);
    }

}
