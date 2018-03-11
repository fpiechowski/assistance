package pl.mesayah.assistance.security.privilege;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivilegeRepository extends CrudRepository<Privilege, Long> {

    Privilege findByName(String name);
}
