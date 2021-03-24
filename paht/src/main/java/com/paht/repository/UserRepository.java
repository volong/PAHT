package com.paht.repository;

import com.paht.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Find user by user name
     * @param username
     *  @return User
     */

    Optional<User> findByUsername(String username);

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

    Boolean existsByEmail(String email);

}
