package pl.mesayah.assistance.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Service responsible for managing chat messages.
 */
@Service
public class MessageService {

    /**
     * Repository fetching chat messages from database.
     */
    @Autowired
    MessageRepository messageRepository;

    /**
     * Default constructor.
     */
    public MessageService() {

    }

    /**
     * Saves a given chat message entity in database.
     *
     * @param message chat massage entity to save.
     */
    public void save(Message message) {

        messageRepository.save(message);
    }

    /**
     * Retrieves a chat message entity by its id.
     *
     * @param id given id
     * @return a chat message entity with the given id or null if none found.
     */
    public Message findById(Long id) {

        return messageRepository.findOne(id);
    }

    /**
     * Retrieves all chat message entities in the given channel.
     *
     * @param channel given channel entity.
     * @return chat message entities in the given channel or null if none found.
     */
    public List<Message> findAllByChannel(Channel channel) {

        return messageRepository.findAllByChannel(channel);
    }

    /**
     * Retrieves all chat message entities in the given channel at a given date.
     *
     * @param channel  given channel entity.
     * @param sendDate given date.
     * @return entities in the given channel at a given date or null if none found.
     */
    public List<Message> findAllByChannelAndSendDate(Channel channel, LocalDateTime sendDate) {

        return messageRepository.findAllByChannelAndSendDateTime(channel, sendDate);
    }

    /**
     * Deletes the chat message entity with the given id.
     *
     * @param id given id.
     */
    public void delete(Long id) {

        messageRepository.delete(id);
    }

    /**
     * Deletes all chat message entities in given channel.
     *
     * @param channel given channel.
     */
    public void delete(Channel channel) {

        messageRepository.deleteAllByChannel(channel);
    }

    /**
     * Deletes all chat message entities before given date.
     *
     * @param sendDateTime given date.
     */
    public void delete(LocalDateTime sendDateTime) {

        messageRepository.deleteAllBySendDateTimeBefore(sendDateTime);
    }
}
