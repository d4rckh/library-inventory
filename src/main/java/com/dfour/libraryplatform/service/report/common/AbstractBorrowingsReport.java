package com.dfour.libraryplatform.service.report.common;

import com.dfour.libraryplatform.domain.dto.requests.ReportRequestDto;
import com.dfour.libraryplatform.entity.BorrowingEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public abstract class AbstractBorrowingsReport extends AbstractReportGeneratorService {

    protected abstract List<BorrowingEntity> getData(final ReportRequestDto reportRequest);

    @Override
    protected List<String[]> getTableData(final ReportRequestDto reportRequest) {
        final List<String[]> tableData = new ArrayList<>();

        // Fetch filtered reservations
        final List<BorrowingEntity> reservations = getData(reportRequest);

        // Add header row
        tableData.add(new String[]{
                "id",
                "book_id",
                "book_title",
                "user_id",
                "user_email",
                "borrow_date",
                "return_date",
                "return_by"
        });

        // Add reservation rows
        reservations.forEach(reservation ->
                tableData.add(new String[]{
                        String.valueOf(reservation.getId()),
                        String.valueOf(reservation.getItem().getBookId()),
                        reservation.getItem().getBook().getTitle(),
                        String.valueOf(reservation.getUserId()),
                        reservation.getUser().getEmail(),
                        String.valueOf(reservation.getBorrowDate()),
                        String.valueOf(reservation.getReturnedDate()),
                        String.valueOf(reservation.getReturnDate())
                })
        );

        return tableData;
    }
}
