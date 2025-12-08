// src/main/java/com/parfumerie/catalog/repository/UtilizatorRepository.java

package com.parfumerie.catalog.repository;

import com.parfumerie.catalog.entity.Utilizator; // Asigura-te ca ai importat entitatea corecta
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
// JpaRepository<Entitate, Tipul_cheii_primare>
public interface UtilizatorRepository extends JpaRepository<Utilizator, Long> {

    /**
     * Spring Data JPA genereaza automat interogarea SQL (SELECT * FROM utilizatori WHERE utilizator = ?)
     * Metoda este esentiala pentru a prelua un utilizator dupa numele de login.
     */
    Optional<Utilizator> findByUtilizator(String utilizator);
}