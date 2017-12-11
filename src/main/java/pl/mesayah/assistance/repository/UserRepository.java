package pl.mesayah.assistance.repository;

import org.springframework.data.repository.CrudRepository;
import pl.mesayah.assistance.domain.Role;
import pl.mesayah.assistance.domain.User;

import java.util.List;

/**
 * Repository for fetching and saving users in the database. It handles database communication.
 */
public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findAllByRole(Role role);

    User findByUsername(String username);
}
