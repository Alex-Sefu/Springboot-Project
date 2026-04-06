package com.parfumerie.catalog.controller;

import com.parfumerie.catalog.dto.AuthRequestDto;
import com.parfumerie.catalog.dto.AuthResponseDto;
import com.parfumerie.catalog.entity.Utilizator;
import com.parfumerie.catalog.repository.UtilizatorRepository;
import com.parfumerie.catalog.security.JwtUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UtilizatorRepository utilizatorRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    public AuthController(AuthenticationManager authenticationManager,
                          UtilizatorRepository utilizatorRepository,
                          PasswordEncoder passwordEncoder,
                          JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.utilizatorRepository = utilizatorRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> authenticateUser(@Valid @RequestBody AuthRequestDto loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );

            Utilizator utilizator = utilizatorRepository.findByUtilizator(loginRequest.getUsername())
                    .orElseThrow();

            String token = jwtUtils.generateToken(utilizator.getUtilizator(), Collections.singletonList(utilizator.getRolul()));
            return ResponseEntity.ok(new AuthResponseDto(token, utilizator.getUtilizator(), utilizator.getRolul()));
        } catch (AuthenticationException ex) {
            return ResponseEntity.status(401).build();
        }
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDto> registerUser(@Valid @RequestBody AuthRequestDto registerRequest) {
        if (utilizatorRepository.findByUtilizator(registerRequest.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        Utilizator user = new Utilizator();
        user.setUtilizator(registerRequest.getUsername());
        user.setNume(registerRequest.getUsername());
        user.setParola(passwordEncoder.encode(registerRequest.getPassword()));
        user.setRolul("ROLE_USER");

        utilizatorRepository.save(user);

        String token = jwtUtils.generateToken(user.getUtilizator(), Collections.singletonList(user.getRolul()));
        return ResponseEntity.ok(new AuthResponseDto(token, user.getUtilizator(), user.getRolul()));
    }
}
