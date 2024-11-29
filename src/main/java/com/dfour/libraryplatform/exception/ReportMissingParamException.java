package com.dfour.libraryplatform.exception;

import com.dfour.libraryplatform.exception.common.HttpException;
import org.springframework.http.HttpStatus;

public class ReportMissingParamException extends HttpException {
    public ReportMissingParamException(final String key) {
        super(HttpStatus.BAD_REQUEST, String.format("Missing required key for report generation: %s", key));
    }
}
