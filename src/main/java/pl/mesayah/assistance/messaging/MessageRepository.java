package pl.mesayah.assistance.messaging;

import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Repository for fetching and saving chat messages in the database. It handles database communication.
 */
public interface MessageRepository extends CrudRepository<Message, Long> {

    List<Message> findAllByChannel(Channel channel);

    List<Message> findAllByChannelAndSendDateTime(Channel channel, LocalDateTime sendDateTime);

    void deleteAllByChannel(Channel channel);

    void deleteAllBySendDateTimeBefore(LocalDateTime sendDateTime);
}
