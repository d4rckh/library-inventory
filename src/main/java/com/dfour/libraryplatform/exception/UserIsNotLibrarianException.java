package com.dfour.libraryplatform.exception;

import com.dfour.libraryplatform.exception.common.HttpException;
import org.springframework.http.HttpStatus;

public class UserIsNotLibrarianException extends HttpException {
    public UserIsNotLibrarianException() {
        super(HttpStatus.FORBIDDEN, "User is not librarian");
    }
}
