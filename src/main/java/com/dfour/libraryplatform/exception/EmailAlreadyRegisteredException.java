package com.dfour.libraryplatform.exception;

import com.dfour.libraryplatform.exception.common.HttpException;
import org.springframework.http.HttpStatus;

public class EmailAlreadyRegisteredException extends HttpException {
    public EmailAlreadyRegisteredException() {
        super(HttpStatus.CONFLICT, "Email already registered");
    }
}
