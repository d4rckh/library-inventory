package com.dfour.libraryplatform.service.report;

import com.dfour.libraryplatform.domain.dto.filters.BorrowingFilterDto;
import com.dfour.libraryplatform.domain.dto.requests.ReportRequestDto;
import com.dfour.libraryplatform.entity.BorrowingEntity;
import com.dfour.libraryplatform.exception.ReportMissingParamException;
import com.dfour.libraryplatform.service.BorrowingService;
import com.dfour.libraryplatform.service.report.common.AbstractBorrowingsReport;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ItemBorrowingsReportGeneratorService extends AbstractBorrowingsReport {
    private final BorrowingService borrowingService;

    @Override
    protected List<BorrowingEntity> getData(final ReportRequestDto reportRequest) {
        return borrowingService.findFiltered(
                BorrowingFilterDto.builder()
                        .itemId(reportRequest.getLong("itemId"))
                        .build()
        );
    }

    @Override
    public boolean canHandle(final ReportRequestDto reportRequest) {
        return Objects.equals(reportRequest.getReportId(), "ITEM_BORROWINGS");
    }
}
