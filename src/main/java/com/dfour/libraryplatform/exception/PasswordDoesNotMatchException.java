package com.dfour.libraryplatform.exception;

import com.dfour.libraryplatform.exception.common.HttpException;
import org.springframework.http.HttpStatus;

public class PasswordDoesNotMatchException extends HttpException {
    public PasswordDoesNotMatchException() {
        super(HttpStatus.FORBIDDEN, "Password does not match");
    }
}
