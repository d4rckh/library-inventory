package com.dfour.libraryplatform.service.report.common;

import com.dfour.libraryplatform.domain.dto.requests.ReportRequestDto;
import com.dfour.libraryplatform.exception.ReportGenerationErrorException;
import com.dfour.libraryplatform.exception.ReportFormatNotImplementedException;

import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

public abstract class AbstractReportGeneratorService {

    protected List<String[]> getTableData(final ReportRequestDto reportRequest) {
        throw new ReportFormatNotImplementedException();
    }

    public void generateCsv(final OutputStream outputStream, final ReportRequestDto reportRequest) {
        final List<String[]> csvData = getTableData(reportRequest);
        if (csvData == null || csvData.isEmpty()) {
            throw new ReportGenerationErrorException("CSV data cannot be null or empty");
        }

        try {
            for (String[] row : csvData) {
                final String csvRow = String.format("%s\n", String.join(",", row));
                outputStream.write(csvRow.getBytes(StandardCharsets.UTF_8));
            }
        } catch (Exception e) {
            throw new ReportGenerationErrorException("Error generating CSV");
        }
    }

    public void generatePdf(final OutputStream outputStream, final ReportRequestDto reportRequest) {
        throw new ReportFormatNotImplementedException();
    }

    public void generateXlsx(final OutputStream outputStream, final ReportRequestDto reportRequest) {
        throw new ReportFormatNotImplementedException();
    }

    public abstract boolean canHandle(final ReportRequestDto reportRequest);

}
