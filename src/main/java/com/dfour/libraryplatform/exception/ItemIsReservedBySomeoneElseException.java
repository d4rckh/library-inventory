package com.dfour.libraryplatform.exception;

import com.dfour.libraryplatform.exception.common.HttpException;
import org.springframework.http.HttpStatus;

public class ItemIsReservedBySomeoneElseException extends HttpException {
    public ItemIsReservedBySomeoneElseException() {
        super(HttpStatus.CONFLICT, "Item is reserved by someone else");
    }
}
