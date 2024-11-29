package com.dfour.libraryplatform.service.report.common;

import com.dfour.libraryplatform.domain.dto.requests.ReportRequestDto;
import com.dfour.libraryplatform.entity.ReservationEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public abstract class AbstractReservationsReport extends AbstractReportGeneratorService {

    protected abstract List<ReservationEntity> getData(final ReportRequestDto reportRequest);

    @Override
    protected List<String[]> getTableData(final ReportRequestDto reportRequest) {
        final List<String[]> tableData = new ArrayList<>();

        // Fetch filtered reservations
        final List<ReservationEntity> reservations = getData(reportRequest);

        // Add header row
        tableData.add(new String[]{
                "id",
                "book_id",
                "book_title",
                "cancelled",
                "user_id",
                "user_email",
                "reservation_date",
                "reservation_pickup",
                "reservation_expiration"
        });

        // Add reservation rows
        reservations.forEach(reservation ->
                tableData.add(new String[]{
                        String.valueOf(reservation.getId()),
                        String.valueOf(reservation.getItem().getBookId()),
                        reservation.getItem().getBook().getTitle(),
                        String.valueOf(reservation.getCancelled()),
                        String.valueOf(reservation.getUserId()),
                        reservation.getUser().getEmail(),
                        String.valueOf(reservation.getCreatedAt()),
                        String.valueOf(reservation.getExpiredAt()),
                        String.valueOf(reservation.getExpiresAt())
                })
        );

        return tableData;
    }
}
