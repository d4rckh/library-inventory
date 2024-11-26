package com.dfour.libraryplatform.repository;

import com.dfour.libraryplatform.entity.UserEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query(value = "SELECT u FROM UserEntity u WHERE (u.email LIKE %:email% OR :email IS NULL)")
    Slice<UserEntity> findFiltered(String email, PageRequest pageRequest);

    Optional<UserEntity> findByEmail(String email);

}
