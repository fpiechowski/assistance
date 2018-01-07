package pl.mesayah.assistance.user;

import org.springframework.data.repository.CrudRepository;

/**
 * Repository for fetching and saving users in the database. It handles database communication.
 */
public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);
}
