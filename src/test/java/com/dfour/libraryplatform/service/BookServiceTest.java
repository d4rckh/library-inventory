package com.dfour.libraryplatform.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ContextConfiguration(classes = BookService.class)
public class BookServiceTest {
    @Autowired
    private BookService bookService;

    @Test
    void getAllBooks() {
        assertEquals(0, bookService.findAll().size());
    }
}
