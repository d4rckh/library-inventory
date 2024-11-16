package com.dfour.libraryplatform.exception;

import com.dfour.libraryplatform.exception.common.HttpException;
import org.springframework.http.HttpStatus;

public class TooManyReservationsException extends HttpException {
    public TooManyReservationsException() {
        super(HttpStatus.TOO_MANY_REQUESTS, "You have too many active reservations");
    }
}
