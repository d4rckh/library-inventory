package com.dfour.libraryplatform.security;

import com.dfour.libraryplatform.entity.UserEntity;
import com.dfour.libraryplatform.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {
    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(final String username) {
        final Optional<UserEntity> optionalUserEntity = userService.findByEmail(username);
        return optionalUserEntity.map(AppUserDetails::new).orElse(null);
    }

}
