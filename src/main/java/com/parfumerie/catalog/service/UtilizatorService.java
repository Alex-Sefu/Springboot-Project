package com.parfumerie.catalog.service;

import com.parfumerie.catalog.entity.Utilizator;
import com.parfumerie.catalog.repository.UtilizatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UtilizatorService {

    private final UtilizatorRepository utilizatorRepository;


    @Autowired
    public UtilizatorService(UtilizatorRepository utilizatorRepository) {
        this.utilizatorRepository = utilizatorRepository;
    }

    public Utilizator saveUtilizator(Utilizator utilizator) {
        return utilizatorRepository.save(utilizator);
    }
}