package com.dfour.libraryplatform.service;

import com.dfour.libraryplatform.domain.dto.AccessInformationResponseDto;
import com.dfour.libraryplatform.domain.dto.UserSignInRequestDto;
import com.dfour.libraryplatform.domain.dto.UserSignUpRequestDto;
import com.dfour.libraryplatform.exception.EmailAlreadyRegisteredException;
import com.dfour.libraryplatform.exception.NotFoundException;
import com.dfour.libraryplatform.exception.PasswordDoesNotMatchException;
import com.dfour.libraryplatform.repository.UserRepository;
import com.dfour.libraryplatform.repository.entity.UserEntity;
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

    public List<UserEntity> findAll() {
        return users.findAll(
                PageRequest.of(0, 100,
                        Sort.by(Sort.Direction.DESC, "id"))
        ).getContent();
    }

    public Optional<UserEntity> findByEmail(String email) {
        return users.findByEmail(email);
    }

    public boolean signUp(UserSignUpRequestDto userSignUpRequestDto) {
        if (users.findByEmail(userSignUpRequestDto.getEmail()).isPresent())
            throw new EmailAlreadyRegisteredException();

        UserEntity savedUser = users.save(UserEntity.builder()
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
}
