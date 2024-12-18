package com.dfour.libraryplatform.service;

import com.dfour.libraryplatform.domain.dto.filters.BookFilterDto;
import com.dfour.libraryplatform.domain.dto.stats.BookStatsDto;
import com.dfour.libraryplatform.entity.BookEntity;
import com.dfour.libraryplatform.entity.UserEntity;
import com.dfour.libraryplatform.exception.NotFoundException;
import com.dfour.libraryplatform.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository books;

    public List<BookEntity> findFiltered(final BookFilterDto filter) {
        return books.findFiltered(
                filter.getTitleSearch().toLowerCase(),
                PageRequest.of(0, 100,
                        Sort.by(Sort.Direction.DESC, "id"))
        ).getContent();
    }

    public void deleteById(final Long id) {
        books.deleteById(id);
    }

    public Optional<BookEntity> findById(final Long id) {
        return books.findById(id);
    }

    public BookEntity patch(final Long bookId, final BookEntity incompleteBookEntity) {
        final BookEntity book = books.findById(bookId).orElseThrow(NotFoundException::new);

        if (!Objects.isNull(incompleteBookEntity.getTags())) {
            book.getTags().clear();
            book.getTags().addAll(incompleteBookEntity.getTags());
        }

        if (!Objects.isNull(incompleteBookEntity.getTitle()))
            book.setTitle(incompleteBookEntity.getTitle());

        if (!Objects.isNull(incompleteBookEntity.getAuthor()))
            book.setAuthor(incompleteBookEntity.getAuthor());

        if (!Objects.isNull(incompleteBookEntity.getIsbn()))
            book.setIsbn(incompleteBookEntity.getIsbn());

        if (!Objects.isNull(incompleteBookEntity.getYear()))
            book.setYear(incompleteBookEntity.getYear());

        if (!Objects.isNull(incompleteBookEntity.getPublisher()))
            book.setPublisher(incompleteBookEntity.getPublisher());

        return books.save(book);
    }

    public BookEntity create(final BookEntity bookEntity) {
        return books.save(bookEntity);
    }

    public BookEntity createAsUser(final BookEntity bookEntity, final UserEntity userEntity) {
        bookEntity.setUserId(userEntity.getId());
        bookEntity.setUser(userEntity);

        return books.save(bookEntity);
    }

    public BookStatsDto bookStats() {
        return BookStatsDto.builder()
                .uniqueTitles(books.count()).build();
    }
}
