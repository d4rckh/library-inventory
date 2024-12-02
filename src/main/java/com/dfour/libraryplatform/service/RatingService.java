package com.dfour.libraryplatform.service;

import com.dfour.libraryplatform.domain.dto.filters.BorrowingFilterDto;
import com.dfour.libraryplatform.domain.dto.filters.RatingFilterDto;
import com.dfour.libraryplatform.entity.BorrowingEntity;
import com.dfour.libraryplatform.entity.RatingEntity;
import com.dfour.libraryplatform.exception.InvalidRatingException;
import com.dfour.libraryplatform.exception.RatingNotReturnedException;
import com.dfour.libraryplatform.repository.RatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RatingService {

    private final RatingRepository ratingRepository;
    private final BorrowingService borrowingService;

    public List<RatingEntity> find(RatingFilterDto filter) {
        return ratingRepository.findFiltered(filter.getUserId(), filter.getBookId(),
                PageRequest.of(0, 100,
                        Sort.by(Sort.Direction.DESC, "id"))).getContent();
    }

    public RatingEntity createOrUpdateRating(
            Long userId, Long bookId, Integer rating
    ) {
        if (rating < 1 || rating > 5) {
            throw new InvalidRatingException();
        }

        List<BorrowingEntity> borrowingEntities = borrowingService.findFiltered(
                BorrowingFilterDto.builder()
                        .bookId(bookId)
                        .userId(userId).build()
        );

        if (borrowingEntities.isEmpty())
            throw new RatingNotReturnedException();

        RatingEntity ratingEntity = RatingEntity.builder()
                .bookId(bookId)
                .userId(userId)
                .rating(rating).build();

        return ratingRepository.save(ratingEntity);
    }

}
