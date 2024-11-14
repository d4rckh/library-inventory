package com.dfour.libraryplatform.reservation;

import com.dfour.libraryplatform.manager.ReservationManager;
import com.dfour.libraryplatform.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
@ActiveProfiles("test")
@Sql("classpath:test-data.sql")
public class ReservationManagerTest {

    @Autowired
    private ReservationManager reservationManager;

    @Autowired
    private BookService bookService;

    @Test
    void reservationManagerShouldReserve() {

    }

}
