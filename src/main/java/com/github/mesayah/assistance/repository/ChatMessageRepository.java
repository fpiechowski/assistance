package com.github.mesayah.assistance.repository;

import com.github.mesayah.assistance.model.Channel;
import com.github.mesayah.assistance.model.ChatMessage;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

/**
 * Repository for fetching and saving chat messages in the database. It handles database communication.
 */
public interface ChatMessageRepository extends CrudRepository<ChatMessage, Long> {
    List<ChatMessage> findAllByChannel(Channel channel);
    List<ChatMessage> findAllByChannelAndSendDate(Channel channel, Date sendDate);
    void deleteAllByChannel(Channel channel);
    void deleteAllBySendDateBefore(Date sendDate);
}
