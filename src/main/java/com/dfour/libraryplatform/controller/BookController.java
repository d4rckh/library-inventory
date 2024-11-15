package com.dfour.libraryplatform.controller;

import com.dfour.libraryplatform.entity.BookEntity;
import com.dfour.libraryplatform.exception.NotFoundException;
import com.dfour.libraryplatform.service.BookService;
import com.dfour.libraryplatform.security.AppUserDetails;
import com.dfour.libraryplatform.security.authentication.AppAuthentication;
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
        return bookService.patch(bookId, incompleteBookEntity);
    }

    @GetMapping("/{bookId}")
    BookEntity getById(
            @PathVariable Long bookId
    ) {
        return bookService.findById(bookId)
                .orElseThrow(NotFoundException::new);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    BookEntity create(@RequestBody BookEntity bookEntity) {
        AppUserDetails appUserDetails = AppAuthentication.GetLoggedUserDetails();
        return bookService.createAsUser(bookEntity, appUserDetails.getEntity());
    }

}
