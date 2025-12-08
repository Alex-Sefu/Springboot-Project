package com.parfumerie.catalog.service;

import com.parfumerie.catalog.entity.Utilizator;
import com.parfumerie.catalog.repository.UtilizatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UtilizatorService {

    private final UtilizatorRepository utilizatorRepository;

    // Injectarea JavaMailSender este ELIMINATĂ

    @Autowired
    public UtilizatorService(UtilizatorRepository utilizatorRepository) {
        this.utilizatorRepository = utilizatorRepository;
    }

    public Utilizator saveUtilizator(Utilizator utilizator) {
        // NU mai setăm 'activat=false'. Contul e activ implicit.
        return utilizatorRepository.save(utilizator);

        // Trimiterea notificării către administrator este ELIMINATĂ
    }

    // Metoda activeazaUtilizator este ELIMINATĂ
}