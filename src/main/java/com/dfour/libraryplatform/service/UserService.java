package com.dfour.libraryplatform.service;

import com.dfour.libraryplatform.domain.dto.AccessInformationResponseDto;
import com.dfour.libraryplatform.domain.dto.filters.UserFilterDto;
import com.dfour.libraryplatform.domain.dto.requests.UserSignInRequestDto;
import com.dfour.libraryplatform.domain.dto.requests.UserSignUpRequestDto;
import com.dfour.libraryplatform.domain.dto.stats.UserStatsDto;
import com.dfour.libraryplatform.entity.UserEntity;
import com.dfour.libraryplatform.exception.EmailAlreadyRegisteredException;
import com.dfour.libraryplatform.exception.NotFoundException;
import com.dfour.libraryplatform.exception.PasswordDoesNotMatchException;
import com.dfour.libraryplatform.repository.UserRepository;
import com.dfour.libraryplatform.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository users;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public Optional<UserEntity> findByEmail(final String email) {
        return users.findByEmail(email);
    }

    public List<UserEntity> findFiltered(final UserFilterDto filter) {
        return users.findFiltered(filter.getEmailSearch(),
                PageRequest.of(0, 100,
                        Sort.by(Sort.Direction.DESC, "id"))).getContent();
    }

    public Optional<UserEntity> findById(final Long id) {
        return users.findById(id);
    }

    public boolean signUp(final UserSignUpRequestDto userSignUpRequestDto) {
        if (users.findByEmail(userSignUpRequestDto.getEmail()).isPresent())
            throw new EmailAlreadyRegisteredException();

        final UserEntity savedUser = users.save(UserEntity.builder()
                .email(userSignUpRequestDto.getEmail())
                .firstName(userSignUpRequestDto.getFirstName())
                .lastName(userSignUpRequestDto.getLastName())
                .hashedPassword(passwordEncoder.encode(userSignUpRequestDto.getPassword()))
                .build()
        );

        return savedUser.getId() > 0;
    }

    public AccessInformationResponseDto signIn(UserSignInRequestDto userSignInRequestDto) {
        UserEntity user = users.findByEmail(userSignInRequestDto.getEmail())
                .orElseThrow(NotFoundException::new);

        if (!passwordEncoder.matches(userSignInRequestDto.getPassword(), user.getHashedPassword())) {
            throw new PasswordDoesNotMatchException();
        }

        return new AccessInformationResponseDto(jwtService.generateToken(user));
    }

    public UserStatsDto userStats() {
        return UserStatsDto.builder()
                .registeredUsers(users.count())
                .build();
    }
}
