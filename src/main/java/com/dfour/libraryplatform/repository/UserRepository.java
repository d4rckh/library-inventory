package com.dfour.libraryplatform.repository;

import com.dfour.libraryplatform.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query(value = "SELECT u FROM UserEntity u WHERE (u.email LIKE %:email% OR :email IS NULL)")
    ArrayList<UserEntity> findFiltered(String email);

    Optional<UserEntity> findByEmail(String email);

}
