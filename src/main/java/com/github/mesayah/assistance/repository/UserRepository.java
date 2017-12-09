package com.github.mesayah.assistance.repository;

import com.github.mesayah.assistance.model.Role;
import com.github.mesayah.assistance.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Repository for fetching and saving users in the database. It handles database communication.
 */
public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findAllByRole(Role role);
    User findByUsername(String username);
}
