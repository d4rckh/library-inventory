package com.dfour.libraryplatform.service;

import com.dfour.libraryplatform.exception.NotFoundException;
import com.dfour.libraryplatform.repository.BookRepository;
import com.dfour.libraryplatform.repository.TagRepository;
import com.dfour.libraryplatform.repository.UserRepository;
import com.dfour.libraryplatform.repository.entity.BookEntity;
import com.dfour.libraryplatform.repository.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository users;

    public List<UserEntity> findAll() {
        return users.findAll(
                PageRequest.of(0, 100,
                        Sort.by(Sort.Direction.DESC, "id"))
        ).getContent();
    }

    public UserEntity create(UserEntity userEntity) {
        return users.save(userEntity);
    }
}
