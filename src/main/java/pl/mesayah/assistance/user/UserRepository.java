package pl.mesayah.assistance.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for fetching and saving users in the database. It handles database communication.
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);

    User findFirstByUsernameContains(String username);



}
