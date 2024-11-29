package com.dfour.libraryplatform.exception;

import com.dfour.libraryplatform.exception.common.HttpException;
import org.springframework.http.HttpStatus;

public class ReportFormatNotImplementedException extends HttpException {
    public ReportFormatNotImplementedException() {
        super(HttpStatus.NOT_IMPLEMENTED, "Report format not implemented for this type");
    }
}
