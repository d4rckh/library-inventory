package com.dfour.libraryplatform.service;

import com.dfour.libraryplatform.domain.dto.BorrowingStatsDto;
import com.dfour.libraryplatform.repository.BorrowingRepository;
import com.dfour.libraryplatform.repository.entity.BorrowingEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BorrowingService {

    private final BorrowingRepository borrowings;

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

    public Optional<BorrowingEntity> isItemBorrowed(long itemId) {
        return borrowings.isItemBorrowed(itemId);
    }

    public BorrowingEntity save(BorrowingEntity borrowingEntity) {
        return borrowings.save(borrowingEntity);
    }
}
