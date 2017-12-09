package com.github.mesayah.assistance.repository;

import com.github.mesayah.assistance.model.Channel;
import com.github.mesayah.assistance.model.Role;
import com.github.mesayah.assistance.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findAllByRole(Role role);
    User findByUsername(String username);
}
