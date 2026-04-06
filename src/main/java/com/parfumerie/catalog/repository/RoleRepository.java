package com.parfumerie.catalog.repository;

import com.parfumerie.catalog.entity.Role;
import com.parfumerie.catalog.entity.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName name);
}
