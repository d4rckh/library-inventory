package com.dfour.libraryplatform.service;

import com.dfour.libraryplatform.domain.dto.requests.ReportRequestDto;
import com.dfour.libraryplatform.exception.ReportFormatNotFoundException;
import com.dfour.libraryplatform.exception.ReportTypeNotFoundException;
import com.dfour.libraryplatform.service.report.common.AbstractReportGeneratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.OutputStream;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportFactoryService {

    private final List<AbstractReportGeneratorService> generatorServices;

    public void generateReport(final OutputStream outputStream, final ReportRequestDto reportRequest) {
        AbstractReportGeneratorService generatorService = generatorServices
                .stream()
                .filter(a -> a.canHandle(reportRequest)).findFirst()
                .orElseThrow(ReportTypeNotFoundException::new);

        switch (reportRequest.getFormat()) {
            case "csv" -> generatorService.generateCsv(outputStream, reportRequest);
            case "pdf" -> generatorService.generatePdf(outputStream, reportRequest);
            case "xlsx" -> generatorService.generateXlsx(outputStream, reportRequest);
            default -> throw new ReportFormatNotFoundException();
        }
    }


}
