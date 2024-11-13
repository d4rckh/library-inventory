package com.dfour.libraryplatform.exception.handler;

import com.dfour.libraryplatform.exception.common.HttpException;
import com.dfour.libraryplatform.exception.common.HttpExceptionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HttpExceptionHandler {

    @ExceptionHandler(HttpException.class)
    ResponseEntity<HttpExceptionResponse> handleHttpException(HttpException e) {
        return ResponseEntity.status(e.getStatus()).body(new HttpExceptionResponse(e));
    }

}
