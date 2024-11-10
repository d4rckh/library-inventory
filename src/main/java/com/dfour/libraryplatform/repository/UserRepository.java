package com.dfour.libraryplatform.repository;

import com.dfour.libraryplatform.repository.entity.BookEntity;
import com.dfour.libraryplatform.repository.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

}
