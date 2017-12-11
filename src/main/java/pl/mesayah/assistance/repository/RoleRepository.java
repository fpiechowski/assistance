package pl.mesayah.assistance.repository;

import org.springframework.data.repository.CrudRepository;
import pl.mesayah.assistance.domain.Role;

/**
 * Repository for fetching and saving roles in the database. It handles database communication.
 */
public interface RoleRepository extends CrudRepository<Role, Long> {

}
