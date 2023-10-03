package com.enigma.tokonyadia.repository;

import com.enigma.tokonyadia.entity.Role;
import com.enigma.tokonyadia.entity.role.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
    Optional<Role> findByRole(ERole role);
}
