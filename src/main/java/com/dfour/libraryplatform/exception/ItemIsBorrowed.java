package com.dfour.libraryplatform.exception;

import com.dfour.libraryplatform.exception.common.HttpException;
import org.springframework.http.HttpStatus;

public class ItemIsBorrowed extends HttpException {
    public ItemIsBorrowed() {
        super(HttpStatus.CONFLICT, "Item is borrowed");
    }
}
