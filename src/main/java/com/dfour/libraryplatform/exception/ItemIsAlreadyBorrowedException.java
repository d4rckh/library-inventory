package com.dfour.libraryplatform.exception;

import com.dfour.libraryplatform.exception.common.HttpException;
import org.springframework.http.HttpStatus;

public class ItemIsAlreadyBorrowedException extends HttpException {
    public ItemIsAlreadyBorrowedException() {
        super(HttpStatus.CONFLICT, "Item is already borrowed");
    }
}
