package com.dfour.libraryplatform.service;

import com.dfour.libraryplatform.domain.dto.AccessInformationResponseDto;
import com.dfour.libraryplatform.domain.dto.UserSignInRequestDto;
import com.dfour.libraryplatform.domain.dto.UserSignUpRequestDto;
import com.dfour.libraryplatform.repository.UserRepository;
import com.dfour.libraryplatform.repository.entity.UserEntity;
import com.dfour.libraryplatform.service.security.JwtService;
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
            return false;

        UserEntity savedUser = users.save(UserEntity.builder()
                        .email(userSignUpRequestDto.getEmail())
                        .firstName(userSignUpRequestDto.getFirstName())
                        .lastName(userSignUpRequestDto.getLastName())
                        .hashedPassword(passwordEncoder.encode(userSignUpRequestDto.getPassword()))
                .build()
        );

        return savedUser.getId() > 0;
    }

    public Optional<AccessInformationResponseDto> signIn(UserSignInRequestDto userSignInRequestDto) {
        Optional<UserEntity> optionalUser = users.findByEmail(userSignInRequestDto.getEmail());

        return optionalUser.map(userEntity -> new AccessInformationResponseDto(jwtService.generateToken(userEntity)));
    }
}
