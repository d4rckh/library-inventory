package com.dfour.libraryplatform.security.authentication;

import com.dfour.libraryplatform.security.AppUserDetails;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AppAuthentication {

    public static AppUserDetails GetLoggedUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return (AppUserDetails) authentication.getPrincipal();
        }
        throw new SecurityException("User is not authenticated");
    }

}
