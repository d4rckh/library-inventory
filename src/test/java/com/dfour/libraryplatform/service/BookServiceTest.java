package com.dfour.libraryplatform.service;

import com.dfour.libraryplatform.repository.entity.BookEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
public class BookServiceTest {

    @Autowired
    private BookService bookService;

    @Test
    void whenFindAll_thenReturn0() {
        assertEquals(0, bookService.findAll().size());
    }

    @Test
    void whenFindById_thenReturnEmpty() {
        assertEquals(Optional.empty(), bookService.findById(1L));
    }

    @Test
    @Sql(scripts = "classpath:test-data.sql")
    void whenFindById_thenReturnBook() {
        Optional<BookEntity> book = bookService.findById(1L);

        assertTrue(book.isPresent());
        assertEquals("Title 1", book.get().getTitle());
        assertEquals("Author 1", book.get().getAuthor());
        assertEquals("ISBN 1", book.get().getIsbn());
        assertEquals("Publisher 1", book.get().getPublisher());
    }
}
