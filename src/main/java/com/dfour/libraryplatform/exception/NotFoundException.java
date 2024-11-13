package com.dfour.libraryplatform.exception;

import com.dfour.libraryplatform.exception.common.HttpException;
import org.springframework.http.HttpStatus;

public class NotFoundException extends HttpException {

    public NotFoundException() {
        super(HttpStatus.NOT_FOUND, "Not found");
    }

}
