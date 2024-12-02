package com.dfour.libraryplatform.security;

import com.dfour.libraryplatform.entity.UserEntity;
import com.dfour.libraryplatform.exception.NotAuthorizedException;
import com.dfour.libraryplatform.exception.UserIsNotLibrarianException;

import java.util.Objects;

import static com.dfour.libraryplatform.security.AppAuthentication.GetLoggedUserDetails;

public class AppAuthorization {

    public static void EnsureUserLibrarian() {
        UserEntity appUserDetails = GetLoggedUserDetails().getEntity();

        if (Objects.equals(appUserDetails.getIsLibrarian(), false))
            throw new UserIsNotLibrarianException();
    }

    public static void EnsureUserId(Long userId) {
        UserEntity appUserDetails = GetLoggedUserDetails().getEntity();

        if (!Objects.equals(appUserDetails.getId(), userId) &&
                Objects.equals(appUserDetails.getIsLibrarian(), false))
            throw new NotAuthorizedException();
    }

}
