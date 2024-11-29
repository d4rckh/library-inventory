package com.dfour.libraryplatform.exception;

import com.dfour.libraryplatform.exception.common.HttpException;
import org.springframework.http.HttpStatus;

public class ReportFormatNotFoundException extends HttpException {
    public ReportFormatNotFoundException() {
        super(HttpStatus.NOT_FOUND, "Report format not found");
    }
}
