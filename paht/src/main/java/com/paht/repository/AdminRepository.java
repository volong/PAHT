package com.paht.repository;

import com.paht.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository  extends JpaRepository<Admin, Long> {

    /**
     * Find user by user name
     * @param username
     *  @return User
     */

    Optional<Admin> findByUsername(String username);

    /**
     * Check exists an user by user name
     * @param username
     * @return Boolean
     */
    Boolean existsByUsername(String username);

    /**
     * Check exists an user email
     * @param email
     * @return Boolean
     */
}
