package com.dfour.libraryplatform.controller;

import com.dfour.libraryplatform.domain.dto.requests.ReportRequestDto;
import com.dfour.libraryplatform.service.ReportFactoryService;
import com.dfour.libraryplatform.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

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
        reportFactoryService.generateReport(response.getOutputStream(),
                ReportRequestDto.builder()
                        .reportId(reportId)
                        .format(format)
                        .parameters(parameters)
                        .build()
                );
    }

}
