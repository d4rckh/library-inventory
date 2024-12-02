package com.dfour.libraryplatform.controller;

import com.dfour.libraryplatform.domain.dto.requests.ReportRequestDto;
import com.dfour.libraryplatform.service.ReportFactoryService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

import static com.dfour.libraryplatform.security.AppAuthorization.EnsureUserLibrarian;

@RestController
@RequiredArgsConstructor
@RequestMapping("/report")
public class ReportController {

    private final ReportFactoryService reportFactoryService;

    @GetMapping
    public void download(
            HttpServletResponse response,
            @RequestParam(name = "reportId") final String reportId,
            @RequestParam(name = "format") final String format,
            @RequestParam final Map<String, String> parameters
    ) throws IOException {
        EnsureUserLibrarian();

        reportFactoryService.generateReport(response.getOutputStream(),
                ReportRequestDto.builder()
                        .reportId(reportId)
                        .format(format)
                        .parameters(parameters)
                        .build()
        );
    }

}
