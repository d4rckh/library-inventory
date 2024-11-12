package com.dfour.libraryplatform.controller;

import com.dfour.libraryplatform.repository.BookRepository;
import com.dfour.libraryplatform.repository.entity.BookEntity;
import com.dfour.libraryplatform.service.BookService;
import com.dfour.libraryplatform.service.security.UserSecurity;
import com.dfour.libraryplatform.service.security.authentication.AppAuthentication;
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
    private List<BookEntity> findAll() {
        return bookService.findAll();
    }

    @PatchMapping("/{bookId}")
    BookEntity patch(
            @PathVariable Long bookId,
            @RequestBody BookEntity incompleteBookEntity
    ) {
        UserSecurity userSecurity = AppAuthentication.GetUserDetails();
        log.info("Updating book as {} ", userSecurity.getUsername());
        return bookService.patch(bookId, incompleteBookEntity);
    }

    @PostMapping
    BookEntity create(@RequestBody BookEntity bookEntity) {
        UserSecurity userSecurity = AppAuthentication.GetUserDetails();
        log.info("Creating book as {} ", userSecurity.getUsername());
        return bookService.create(bookEntity);
    }

}
