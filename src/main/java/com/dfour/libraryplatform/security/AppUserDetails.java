package com.dfour.libraryplatform.security;

import com.dfour.libraryplatform.entity.UserEntity;
import lombok.Getter;
import org.springframework.security.core.userdetails.User;

import java.util.Collections;

@Getter
public class AppUserDetails extends User {

    private final UserEntity entity;

    public AppUserDetails(final UserEntity entity) {
        super(entity.getEmail(), "", Collections.emptyList());
        this.entity = entity;
    }

}
