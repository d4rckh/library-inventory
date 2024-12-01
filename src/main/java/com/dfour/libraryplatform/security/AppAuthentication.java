package com.dfour.libraryplatform.security;

import com.dfour.libraryplatform.exception.UserIsNotAuthenticatedException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AppAuthentication {

    public static AppUserDetails GetLoggedUserDetails() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return (AppUserDetails) authentication.getPrincipal();
        }
        throw new UserIsNotAuthenticatedException();
    }

}
