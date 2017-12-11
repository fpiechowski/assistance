package pl.mesayah.assistance.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mesayah.assistance.domain.Channel;
import pl.mesayah.assistance.domain.ChatMessage;
import pl.mesayah.assistance.repository.ChatMessageRepository;

import java.util.Date;
import java.util.List;

/**
 * Service responsible for managing chat messages.
 */
@Service
public class ChatMessageService {

    /**
     * Repository fetching chat messages from database.
     */
    @Autowired
    ChatMessageRepository chatMessageRepository;

    /**
     * Default constructor.
     */
    public ChatMessageService() {

    }

    /**
     * Saves a given chat message entity in database.
     *
     * @param chatMessage chat massage entity to save.
     */
    public void save(ChatMessage chatMessage) {

        chatMessageRepository.save(chatMessage);
    }

    /**
     * Retrieves a chat message entity by its id.
     *
     * @param id given id
     * @return a chat message entity with the given id or null if none found.
     */
    public ChatMessage findById(Long id) {

        return chatMessageRepository.findOne(id);
    }

    /**
     * Retrieves all chat message entities in the given channel.
     *
     * @param channel given channel entity.
     * @return chat message entities in the given channel or null if none found.
     */
    public List<ChatMessage> findAllByChannel(Channel channel) {

        return chatMessageRepository.findAllByChannel(channel);
    }

    /**
     * Retrieves all chat message entities in the given channel at a given date.
     *
     * @param channel  given channel entity.
     * @param sendDate given date.
     * @return entities in the given channel at a given date or null if none found.
     */
    public List<ChatMessage> findAllByChannelAndSendDate(Channel channel, Date sendDate) {

        return chatMessageRepository.findAllByChannelAndSendDate(channel, sendDate);
    }

    /**
     * Deletes the chat message entity with the given id.
     *
     * @param id given id.
     */
    public void delete(Long id) {

        chatMessageRepository.delete(id);
    }

    /**
     * Deletes all chat message entities in given channel.
     *
     * @param channel given channel.
     */
    public void delete(Channel channel) {

        chatMessageRepository.deleteAllByChannel(channel);
    }

    /**
     * Deletes all chat message entities before given date.
     *
     * @param sendDate given date.
     */
    public void delete(Date sendDate) {

        chatMessageRepository.deleteAllBySendDateBefore(sendDate);
    }
}
