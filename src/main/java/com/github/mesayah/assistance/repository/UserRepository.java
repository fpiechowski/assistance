package com.github.mesayah.assistance.repository;

import com.github.mesayah.assistance.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
