package com.paht.repository;

import com.paht.model.ERole;
import com.paht.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    /**
     * Find role by name
     * @param name
     * @return Role
     */
    Optional<Role> findByName(ERole name);
}
