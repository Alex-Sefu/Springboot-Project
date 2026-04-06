package com.parfumerie.catalog.service;

import com.parfumerie.catalog.entity.RoleName;
import com.parfumerie.catalog.entity.User;
import com.parfumerie.catalog.repository.RoleRepository;
import com.parfumerie.catalog.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User saveUser(User user) {
        if (user.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        if (user.getRoles() == null || user.getRoles().isEmpty()) {
            roleRepository.findByName(RoleName.ROLE_USER)
                    .ifPresent(role -> user.setRoles(new HashSet<>(Set.of(role))));
        }

        return userRepository.save(user);
    }
}
