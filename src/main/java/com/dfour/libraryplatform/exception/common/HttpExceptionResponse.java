package com.dfour.libraryplatform.exception.common;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.OffsetDateTime;

@Data
public class HttpExceptionResponse {

    final private HttpStatus status;
    final private String message;
    final private OffsetDateTime timestamp;

    public HttpExceptionResponse(HttpException exception) {
        this.status = exception.getStatus();
        this.message = exception.getMessage();
        this.timestamp = OffsetDateTime.now();
    }

}
