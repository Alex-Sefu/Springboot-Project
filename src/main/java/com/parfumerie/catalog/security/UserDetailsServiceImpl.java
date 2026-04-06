// src/main/java/com/parfumerie/catalog/security/UserDetailsServiceImpl.java

package com.parfumerie.catalog.security;

import com.parfumerie.catalog.repository.UtilizatorRepository;
import com.parfumerie.catalog.entity.Utilizator;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UtilizatorRepository utilizatorRepository;

    // Injectarea Repository-ului prin constructor
    public UserDetailsServiceImpl(UtilizatorRepository utilizatorRepository) {
        this.utilizatorRepository = utilizatorRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Cauta utilizatorul în baza de date (tabela utilizatori)
        Utilizator utilizator = utilizatorRepository.findByUtilizator(username)
                .orElseThrow(() -> new UsernameNotFoundException("Utilizatorul " + username + " nu a fost găsit!"));

        // Adapteaza entitatea Utilizator la obiectul UserDetails cerut de Spring Security
        // Permitem aici și roluri separate prin virgulă sau fără prefix
        List<SimpleGrantedAuthority> authorities = Arrays.stream(utilizator.getRolul().split(","))
                .map(String::trim)
                .filter(role -> !role.isEmpty())
                .map(role -> role.startsWith("ROLE_") ? role : "ROLE_" + role)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(
                utilizator.getUtilizator(),
                utilizator.getParola(),
                authorities
        );
    }
}