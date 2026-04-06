package com.parfumerie.catalog.repository;

import com.parfumerie.catalog.entity.Parfum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
// Extinde JpaSpecificationExecutor pentru a folosi interogari dinamice (Specifications)
public interface ParfumRepository extends JpaRepository<Parfum, Long>, JpaSpecificationExecutor<Parfum> {

    Page<Parfum> findAll(Pageable pageable);

    @Query("SELECT p FROM Parfum p WHERE " +
           "(:brand IS NULL OR LOWER(p.brand) LIKE LOWER(CONCAT('%', :brand, '%'))) AND " +
           "(:creator IS NULL OR LOWER(p.creator) LIKE LOWER(CONCAT('%', :creator, '%')))")
    Page<Parfum> findByBrandContainingIgnoreCaseAndCreatorContainingIgnoreCase(
        @Param("brand") String brand,
        @Param("creator") String creator,
        Pageable pageable);
}