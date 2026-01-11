package com.example.musicapp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // allow frontend files
                        .requestMatchers(
                                "/",
                                "/index.html",
                                "/style.css",
                                "/songs/**"
                        ).permitAll()

                        // allow auth APIs
                        .requestMatchers("/auth/**").permitAll()

                        // allow playlist APIs AFTER LOGIN
                        .requestMatchers("/playlists/**").permitAll()

                        // anything else
                        .anyRequest().authenticated()
                );

        return http.build();
    }
}
