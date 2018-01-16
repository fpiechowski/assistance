package pl.mesayah.assistance.messaging;

// TODO: Implement this.

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Filip Piechowski
 */

/**
 * Service responsible for managing chat channels. It performs automatic channel creation for all
 * {@link Discussable} objects.
 */
@Service
public class ChannelService {

    @Autowired
    private ChannelRepository channelRepository;

    /**
     * Saves given channel to the repository.
     *
     * @param channel a channel to save
     * @return a channel object after saving
     */
    public Channel save(Channel channel) {

        return channelRepository.save(channel);
    }

    /**
     * Finds a channel with given ID in the repository.
     *
     * @param id an ID of a channel to find
     * @return a channel with a given ID or null if channel not found
     */
    public Channel findById(long id) {

        return channelRepository.findOne(id);
    }

    /**
     * Finds channel with given channel NAME.
     *
     * @param name NAME of channels to find
     * @return a list of channels with given NAME
     */
    public List<Channel> findByName(String name) {

        return channelRepository.findByName(name);
    }

    /**
     * Finds all channels.
     * @return a list of all channels
     */
    public List<Channel> findAll() {

        return (List<Channel>) channelRepository.findAll();
    }

    /**
     * Deletes a given channel from the repository.
     *
     * @param channel a channel to delete
     */
    public void delete(Channel channel) {

        channelRepository.delete(channel);
    }

    /**
     * Deletes a channel with a given ID.
     *
     * @param id an ID of a channel to delete
     */
    public void delete(long id) {

        channelRepository.delete(id);
    }
}
