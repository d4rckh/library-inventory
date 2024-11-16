package com.dfour.libraryplatform.exception;

import com.dfour.libraryplatform.exception.common.HttpException;
import org.springframework.http.HttpStatus;

public class ItemIsBorrowedException extends HttpException {
    public ItemIsBorrowedException() {
        super(HttpStatus.CONFLICT, "Item is borrowed");
    }
}
