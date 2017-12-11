package pl.mesayah.assistance.domain;

/**
 * An interface for objects that can be discussed through a messaging service.
 * <p>
 * They have their own {@link Channel} and this channel is automatically created by
 * {@link pl.mesayah.assistance.messaging.ChannelService} when a discussable object is created.
 */
public interface Discussable {

    /**
     * @return a chat channel where this discussable object is discussed
     */
    Channel getChannel();
}
