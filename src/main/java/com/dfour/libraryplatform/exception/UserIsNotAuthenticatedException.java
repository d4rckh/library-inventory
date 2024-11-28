package com.dfour.libraryplatform.exception;

import com.dfour.libraryplatform.exception.common.HttpException;
import org.springframework.http.HttpStatus;

public class UserIsNotAuthenticatedException extends HttpException {
    public UserIsNotAuthenticatedException() {
        super(HttpStatus.UNAUTHORIZED, "Not authenticated");
    }
}
