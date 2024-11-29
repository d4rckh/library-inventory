package com.dfour.libraryplatform.exception;

import com.dfour.libraryplatform.exception.common.HttpException;
import org.springframework.http.HttpStatus;

public class ReportTypeNotFoundException extends HttpException {
    public ReportTypeNotFoundException() {
        super(HttpStatus.NOT_FOUND, "Report type not found");
    }
}
