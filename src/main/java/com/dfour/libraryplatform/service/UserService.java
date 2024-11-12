package com.dfour.libraryplatform.service;

import com.dfour.libraryplatform.domain.dto.AccessInformationResponseDto;
import com.dfour.libraryplatform.domain.dto.UserSignInDto;
import com.dfour.libraryplatform.domain.dto.UserSignUpDto;
import com.dfour.libraryplatform.repository.UserRepository;
import com.dfour.libraryplatform.repository.entity.UserEntity;
import com.dfour.libraryplatform.service.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
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

    public boolean signUp(UserSignUpDto userSignUpDto) {
        if (users.findByEmail(userSignUpDto.getEmail()).isPresent())
            return false;

        UserEntity savedUser = users.save(UserEntity.builder()
                        .email(userSignUpDto.getEmail())
                        .firstName(userSignUpDto.getFirstName())
                        .lastName(userSignUpDto.getLastName())
                        .hashedPassword(passwordEncoder.encode(userSignUpDto.getPassword()))
                .build()
        );

        return savedUser.getId() > 0;
    }

    public Optional<AccessInformationResponseDto> signIn(UserSignInDto userSignInDto) {
        Optional<UserEntity> optionalUser = users.findByEmail(userSignInDto.getEmail());

        return optionalUser.map(userEntity -> new AccessInformationResponseDto(jwtService.generateToken(userEntity)));
    }
}
