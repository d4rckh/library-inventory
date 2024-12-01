package com.dfour.libraryplatform.security;

import com.dfour.libraryplatform.entity.UserEntity;
import com.dfour.libraryplatform.exception.NotAuthorizedException;
import com.dfour.libraryplatform.exception.UserIsNotLibrarianException;

import java.util.Objects;

public class AppAuthorization {

    public static void EnsureUserLibrarian() {
        UserEntity appUserDetails = AppAuthentication.GetLoggedUserDetails().getEntity();

        if (Objects.equals(appUserDetails.getIsLibrarian(), false))
            throw new UserIsNotLibrarianException();
    }

    public static void EnsureUserId(Long userId) {
        UserEntity appUserDetails = AppAuthentication.GetLoggedUserDetails().getEntity();

        if (!Objects.equals(appUserDetails.getId(), userId) &&
                Objects.equals(appUserDetails.getIsLibrarian(), false))
            throw new NotAuthorizedException();
    }

}
