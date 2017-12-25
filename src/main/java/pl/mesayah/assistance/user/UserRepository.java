package pl.mesayah.assistance.user;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Repository for fetching and saving users in the database. It handles database communication.
 */
public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findAllByRole(String role);

    User findByUsername(String username);
}
