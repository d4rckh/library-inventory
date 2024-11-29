package com.dfour.libraryplatform.exception;

import com.dfour.libraryplatform.exception.common.HttpException;
import org.springframework.http.HttpStatus;

public class ReportGenerationErrorException extends HttpException {
    public ReportGenerationErrorException(final String message) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, message);
    }
}
