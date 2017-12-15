package pl.mesayah.assistance.repository;

import org.springframework.data.repository.CrudRepository;
import pl.mesayah.assistance.domain.Channel;

import java.util.List;

/**
 * Repository for fetching and saving channels in the database. It handles database communication.
 */
public interface ChannelRepository extends CrudRepository<Channel, Long> {

    List<Channel> findByName(String name);
}
