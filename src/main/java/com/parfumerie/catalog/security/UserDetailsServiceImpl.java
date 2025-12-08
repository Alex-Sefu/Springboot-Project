// src/main/java/com/parfumerie/catalog/security/UserDetailsServiceImpl.java

package com.parfumerie.catalog.security;

import com.parfumerie.catalog.repository.UtilizatorRepository;
import com.parfumerie.catalog.entity.Utilizator;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections; // Import necesar

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UtilizatorRepository utilizatorRepository;

    // Injectarea Repository-ului prin constructor
    public UserDetailsServiceImpl(UtilizatorRepository utilizatorRepository) {
        this.utilizatorRepository = utilizatorRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Caută utilizatorul în baza de date (tabela utilizatori)
        Utilizator utilizator = utilizatorRepository.findByUtilizator(username)
                .orElseThrow(() -> new UsernameNotFoundException("Utilizatorul " + username + " nu a fost găsit!"));

        // Adaptează entitatea Utilizator la obiectul UserDetails cerut de Spring Security
        // Rolurile sunt tratate ca o colecție de GrantedAuthority
        return new org.springframework.security.core.userdetails.User(
                utilizator.getUtilizator(),
                utilizator.getParola(),
                Collections.singletonList(new SimpleGrantedAuthority(utilizator.getRolul()))
        );
    }
}