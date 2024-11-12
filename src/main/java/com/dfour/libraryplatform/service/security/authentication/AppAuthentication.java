package com.dfour.libraryplatform.service.security.authentication;

import com.dfour.libraryplatform.service.security.UserSecurity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AppAuthentication {

    public static UserSecurity GetUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return (UserSecurity)authentication.getPrincipal();
        }
        throw new SecurityException("User is not authenticated");
    }

}
