package com.parfumerie.catalog.repository;

import com.parfumerie.catalog.entity.Parfum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
// Extinde JpaSpecificationExecutor pentru a folosi interogari dinamice (Specifications)
public interface ParfumRepository extends JpaRepository<Parfum, Long>, JpaSpecificationExecutor<Parfum> {

}