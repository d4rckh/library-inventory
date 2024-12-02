package com.dfour.libraryplatform.domain.dto.requests;

import com.dfour.libraryplatform.exception.ReportMissingParamException;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class ReportRequestDto {

    private String reportId;
    private String format;

    private Map<String, String> parameters;

    public Long getLong(String key) {
        if (!parameters.containsKey(key))
            throw new ReportMissingParamException(key);

        return Long.parseLong(parameters.get(key));
    }

}
