package com.llsoftwaresolutions.gerep_api.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ConfiguracionSeguridad {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/usuarios/**").permitAll()
                        .requestMatchers("/alumnos/**").permitAll()
                        .requestMatchers("/asistencias/**").permitAll()
                        .requestMatchers("/grupos/**").permitAll()
                        .requestMatchers("/contactos-emergencia/**").permitAll()
                        .requestMatchers("/incidencias/**").permitAll()
                        .requestMatchers("/api/reportes/**").permitAll()

                        .anyRequest().authenticated());
        return http.build();
    }
}
