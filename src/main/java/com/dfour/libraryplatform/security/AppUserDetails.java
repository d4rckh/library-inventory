package com.dfour.libraryplatform.security;

import com.dfour.libraryplatform.repository.entity.UserEntity;
import org.springframework.security.core.userdetails.User;

import java.util.Collections;

public class AppUserDetails extends User {

    private final UserEntity entity;

    public AppUserDetails(UserEntity entity) {
        super(entity.getEmail(), null, Collections.emptyList());
        this.entity = entity;
    }

}
