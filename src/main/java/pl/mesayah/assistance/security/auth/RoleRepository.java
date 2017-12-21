package pl.mesayah.assistance.security.auth;

import org.springframework.data.repository.CrudRepository;

/**
 * Repository for fetching and saving roles in the database. It handles database communication.
 */
public interface RoleRepository extends CrudRepository<Role, Long> {

}
