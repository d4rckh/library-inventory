package com.dfour.libraryplatform.exception;

import com.dfour.libraryplatform.exception.common.HttpException;
import org.springframework.http.HttpStatus;

public class RatingNotReturnedException extends HttpException {
    public RatingNotReturnedException() {
        super(HttpStatus.BAD_REQUEST, "You must have borrowed and returned an item of the book to rate it");
    }
}
