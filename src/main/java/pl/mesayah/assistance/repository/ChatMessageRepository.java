package pl.mesayah.assistance.repository;

import org.springframework.data.repository.CrudRepository;
import pl.mesayah.assistance.domain.Channel;
import pl.mesayah.assistance.domain.ChatMessage;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Repository for fetching and saving chat messages in the database. It handles database communication.
 */
public interface ChatMessageRepository extends CrudRepository<ChatMessage, Long> {

    List<ChatMessage> findAllByChannel(Channel channel);

    List<ChatMessage> findAllByChannelAndSendDateTime(Channel channel, LocalDateTime sendDateTime);

    void deleteAllByChannel(Channel channel);

    void deleteAllBySendDateTimeBefore(LocalDateTime sendDateTime);
}
