package com.example.springoauthserver.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    UserDetailsService inMemoryUserDetailsManager() {
        var userBuilder = User.builder();
        UserDetails user =
                userBuilder
                        .username("user")
                        .password("{bcrypt}$2a$10$Smgk4iacoRi6vHRxFUf47OIJferGMqKSG37yXRXHcVj3HkA7LU8n2")
                        .roles("USER", "ADMIN")
                        .build();
        return new InMemoryUserDetailsManager(user);
    }
}
