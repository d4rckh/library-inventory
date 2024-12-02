package com.dfour.libraryplatform.exception;

import com.dfour.libraryplatform.exception.common.HttpException;
import org.springframework.http.HttpStatus;

public class InvalidRatingException extends HttpException {
    public InvalidRatingException() {
        super(HttpStatus.BAD_REQUEST, "Rating should be between 1 and 5");
    }
}
