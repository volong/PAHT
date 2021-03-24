package com.paht.repository;

import com.paht.model.Interface.IStaticalDep;
import com.paht.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface DepRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT id, fullname, username, email FROM users INNER JOIN user_roles ON users.id = user_roles.user_id WHERE user_roles.role_id = 2", nativeQuery = true)
    List<IStaticalDep> findDep();
}
