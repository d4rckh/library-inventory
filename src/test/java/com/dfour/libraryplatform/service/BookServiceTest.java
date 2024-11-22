package com.dfour.libraryplatform.service;

import com.dfour.libraryplatform.domain.dto.filters.BookFilterDto;
import com.dfour.libraryplatform.entity.BookEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Sql(scripts = "classpath:test-data.sql")
public class BookServiceTest {

    @Autowired
    private BookService bookService;

    @Test
    void whenFindFiltered_thenReturnCount() {
        assertEquals(2, bookService.findFiltered(
                BookFilterDto.builder().build()
        ).size());
    }

    @Test
    void whenFindById_thenReturnEmpty() {
        assertTrue(bookService.findById(3L).isEmpty());
    }

    @Test
    void whenFindById_thenReturnBook() {
        Optional<BookEntity> book = bookService.findById(1L);

        assertTrue(book.isPresent());
        assertEquals("Title 1", book.get().getTitle());
        assertEquals("Author 1", book.get().getAuthor());
        assertEquals("ISBN 1", book.get().getIsbn());
        assertEquals("Publisher 1", book.get().getPublisher());
    }
}
