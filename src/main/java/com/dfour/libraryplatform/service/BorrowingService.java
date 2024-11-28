package com.dfour.libraryplatform.service;

import com.dfour.libraryplatform.domain.dto.filters.BorrowingFilterDto;
import com.dfour.libraryplatform.domain.dto.requests.ExtendBorrowingRequestDto;
import com.dfour.libraryplatform.domain.dto.stats.BorrowingStatsDto;
import com.dfour.libraryplatform.domain.dto.stats.ItemBorrowingStatsDto;
import com.dfour.libraryplatform.entity.BorrowingEntity;
import com.dfour.libraryplatform.exception.NotFoundException;
import com.dfour.libraryplatform.repository.BorrowingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BorrowingService {

    private final BorrowingRepository borrowings;

    public ItemBorrowingStatsDto getStats(final Long itemId) {
        return ItemBorrowingStatsDto.builder()
                .borrowed(borrowings.getItemValidBorrowing(itemId).isPresent())
                .times(borrowings.countByItemId(itemId))
                .build();
    }

    public BorrowingEntity markAsReturned(final Long borrowingId) {
        final BorrowingEntity entity = borrowings.findById(borrowingId).orElseThrow(
                NotFoundException::new
        );

        entity.setReturnedDate(OffsetDateTime.now());

        borrowings.save(entity);
        return entity;
    }

    public List<BorrowingEntity> findByUserId(final Long userId) {
        return borrowings.findByUserId(userId,
                PageRequest.of(0, 100,
                        Sort.by(Sort.Direction.DESC, "id"))
        ).getContent();
    }

    public Optional<BorrowingEntity> getItemValidBorrowing(final Long itemId) {
        return borrowings.getItemValidBorrowing(itemId);
    }

    public BorrowingEntity save(final BorrowingEntity borrowingEntity) {
        return borrowings.save(borrowingEntity);
    }

    public BorrowingStatsDto getBorrowingStats() {
        return BorrowingStatsDto.builder()
                .currentBorrows(borrowings.countNotReturned())
                .currentLateBorrows(borrowings.countLateBorrows())
                .build();
    }

    public List<BorrowingEntity> findFiltered(final BorrowingFilterDto filter) {
        return borrowings.findFiltered(filter.getUserId(), filter.getItemId(), filter.getIsReturned(),
                PageRequest.of(0, 100,
                        Sort.by(Sort.Direction.DESC, "id"))).getContent();
    }

    public BorrowingEntity extendBorrowing(final ExtendBorrowingRequestDto requestDto) {
        final BorrowingEntity borrowingEntity = borrowings.findById(requestDto.getBorrowingId())
                .orElseThrow(NotFoundException::new);

        borrowingEntity.setReturnDate(
                borrowingEntity.getReturnDate().plusDays(requestDto.getExtendDays())
        );

        return borrowings.save(borrowingEntity);
    }

}
