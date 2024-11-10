package com.dfour.libraryplatform.service;

import com.dfour.libraryplatform.exception.NotFoundException;
import com.dfour.libraryplatform.repository.BookRepository;
import com.dfour.libraryplatform.repository.TagRepository;
import com.dfour.libraryplatform.repository.entity.BookEntity;
import com.dfour.libraryplatform.repository.entity.TagEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository books;

    public List<BookEntity> findAll(List<String> tags) {
        return books.findAll(
                tags, tags.isEmpty(), tags.size(),
                PageRequest.of(0, 100,
                        Sort.by(Sort.Direction.DESC, "id"))
        ).getContent();
    }

    public List<BookEntity> findAll() {
        return this.findAll(new ArrayList<>());
    }

    public BookEntity patch(long bookId, BookEntity incompleteBookEntity) {
        BookEntity book = books.findById(bookId).orElseThrow(NotFoundException::new);

        if (!Objects.isNull(incompleteBookEntity.getTags())) {
            book.getTags().clear();
            book.getTags().addAll(incompleteBookEntity.getTags());
        }
        if (!Objects.isNull(incompleteBookEntity.getTitle())) {
            book.setTitle(incompleteBookEntity.getTitle());
        }
        if (!Objects.isNull(incompleteBookEntity.getAuthor())) {
            book.setAuthor(incompleteBookEntity.getAuthor());
        }
        if (!Objects.isNull(incompleteBookEntity.getIsbn())) {
            book.setIsbn(incompleteBookEntity.getIsbn());
        }
        if (!Objects.isNull(incompleteBookEntity.getPublishedDate())) {
            book.setPublishedDate(incompleteBookEntity.getPublishedDate());
        }
        if (!Objects.isNull(incompleteBookEntity.getPublisher())) {
            book.setPublisher(incompleteBookEntity.getPublisher());
        }

        return books.save(book);
    }

    public BookEntity create(BookEntity bookEntity) {
        return books.save(bookEntity);
    }
}
