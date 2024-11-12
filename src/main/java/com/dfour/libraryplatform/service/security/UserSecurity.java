package com.dfour.libraryplatform.service.security;

import org.springframework.security.core.userdetails.User;

import java.util.Collections;

public class UserSecurity extends User {

    private final Long id;

    public UserSecurity(final Long id, final String password, final String email) {
        super(email, password, Collections.emptyList());
        this.id = id;
    }

}
