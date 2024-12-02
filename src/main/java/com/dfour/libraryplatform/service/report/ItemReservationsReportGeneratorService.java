package com.dfour.libraryplatform.service.report;

import com.dfour.libraryplatform.domain.dto.filters.ReservationFilterDto;
import com.dfour.libraryplatform.domain.dto.requests.ReportRequestDto;
import com.dfour.libraryplatform.entity.ReservationEntity;
import com.dfour.libraryplatform.service.ReservationService;
import com.dfour.libraryplatform.service.report.common.AbstractReservationsReport;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ItemReservationsReportGeneratorService extends AbstractReservationsReport {
    private final ReservationService reservationService;

    @Override
    protected List<ReservationEntity> getData(final ReportRequestDto reportRequest) {
        return reservationService.findFiltered(
                ReservationFilterDto.builder()
                        .itemId(reportRequest.getLong("itemId"))
                        .build()
        );
    }

    @Override
    public boolean canHandle(final ReportRequestDto reportRequest) {
        return Objects.equals(reportRequest.getReportId(), "ITEM_RESERVATIONS");
    }
}
