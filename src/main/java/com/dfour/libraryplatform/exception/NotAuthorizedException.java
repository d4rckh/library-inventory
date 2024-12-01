package com.dfour.libraryplatform.exception;

import com.dfour.libraryplatform.exception.common.HttpException;
import org.springframework.http.HttpStatus;

public class NotAuthorizedException extends HttpException {
    public NotAuthorizedException() {
        super(HttpStatus.FORBIDDEN, "Not authorized");
    }
}
