package com.dfour.libraryplatform.exception;

import com.dfour.libraryplatform.exception.common.HttpException;
import org.springframework.http.HttpStatus;

public class ItemIsReservedByYouException extends HttpException {
    public ItemIsReservedByYouException() {
        super(HttpStatus.CONFLICT, "Item is already reserved by you");
    }
}
