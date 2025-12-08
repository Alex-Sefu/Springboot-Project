package com.parfumerie.catalog.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final UserDetailsService userDetailsService;

    // Injectăm UserDetailsService în constructorul clasei
    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    // 1. Password Encoder (Dezactivat temporar)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 2. Metoda care creează Authentication Provider (FIXUL FINAL care compilează)
    // NU MAI INJECTAM PasswordEncoder ca argument al metodei.
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {

        // REVENIM la constructorul cu UN SINGUR argument (UserDetailsService),
        // care este cerut de compilator: required: UserDetailsService
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(this.userDetailsService);

        // Acum setăm explicit PasswordEncoder-ul prin setter (fără eroare de compilare)
        provider.setPasswordEncoder(passwordEncoder());

        return provider;
    }

    // 3. Configurarea filtrelor de securitate și a rutării
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        // PERMITE ACCESUL NEAUTENTIFICAT LA PAGINA DE ÎNREGISTRARE (FIXUL DE ROUTING)
                        .requestMatchers("/inregistrare", "/login", "/").permitAll()

                        // Permite accesul la resursele statice (CSS, imagini)
                        .requestMatchers("/*.css", "/*.png", "/*.jpg", "/*.gif").permitAll()

                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/parfumuri", true)
                        .permitAll()
                )
                .logout((logout) -> logout
                        .logoutSuccessUrl("/login")
                        .permitAll()
                );

        return http.build();
    }
}