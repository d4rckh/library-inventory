package com.dfour.libraryplatform.service;

import com.dfour.libraryplatform.domain.dto.BorrowingRequestDto;
import com.dfour.libraryplatform.domain.dto.BorrowingStatsDto;
import com.dfour.libraryplatform.exception.ItemIsAlreadyBorrowedException;
import com.dfour.libraryplatform.repository.BorrowingRepository;
import com.dfour.libraryplatform.repository.entity.BorrowingEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BorrowingService {

    private final BorrowingRepository borrowings;

    public BorrowingEntity create(BorrowingRequestDto borrowingRequest) {
        if (borrowings.isItemBorrowed(borrowingRequest.getItemId()).isPresent())
            throw new ItemIsAlreadyBorrowedException();

        BorrowingEntity borrowingEntity = new BorrowingEntity();
        borrowingEntity.setBorrowDate(OffsetDateTime.now(ZoneOffset.UTC));
        borrowingEntity.setReturnDate(borrowingEntity.getBorrowDate().plusWeeks(2));
        borrowingEntity.setUserId(borrowingRequest.getUserId());
        borrowingEntity.setItemId(borrowingRequest.getItemId());

        return borrowings.save(borrowingEntity);
    }

    public BorrowingStatsDto getStats(long itemId) {
        return BorrowingStatsDto.builder()
                .borrowed(borrowings.isItemBorrowed(itemId).isPresent())
                .times(borrowings.countByItemId(itemId))
                .build();
    }

    public List<BorrowingEntity> findByUserId(long userId) {
        return borrowings.findByUserId(userId,
                PageRequest.of(0, 100,
                        Sort.by(Sort.Direction.DESC, "id"))
        ).getContent();
    }
}
