package pl.mesayah.assistance.repository;

import org.springframework.data.repository.CrudRepository;
import pl.mesayah.assistance.domain.Privilege;

/**
 * Repository for fetching and saving privileges in the database. It handles database communication.
 */
public interface PrivilegeRepository extends CrudRepository<Privilege, Long> {

}
