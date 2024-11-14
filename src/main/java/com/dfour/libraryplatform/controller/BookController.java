package com.dfour.libraryplatform.controller;

import com.dfour.libraryplatform.repository.entity.BookEntity;
import com.dfour.libraryplatform.security.AppUserDetails;
import com.dfour.libraryplatform.security.authentication.AppAuthentication;
import com.dfour.libraryplatform.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        AppUserDetails appUserDetails = AppAuthentication.GetLoggedUserDetails();
        log.info("Updating book as {} ", appUserDetails.getUsername());
        return bookService.patch(bookId, incompleteBookEntity);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    BookEntity create(@RequestBody BookEntity bookEntity) {
        AppUserDetails appUserDetails = AppAuthentication.GetLoggedUserDetails();
        log.info("Creating book as {} ", appUserDetails.getUsername());
        return bookService.create(bookEntity);
    }

}
