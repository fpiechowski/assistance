package com.github.mesayah.assistance.repository;

import com.github.mesayah.assistance.model.Channel;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository for fetching and saving channels in the database. It handles database communication.
 */
public interface ChannelRepository extends CrudRepository<Channel, Long> {
}
