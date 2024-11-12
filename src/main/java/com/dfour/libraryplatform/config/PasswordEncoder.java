package com.dfour.libraryplatform.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class PasswordEncoder {
    @Bean
    org.springframework.security.crypto.password.PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
