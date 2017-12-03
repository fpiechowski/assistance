package com.github.mesayah.assistance.model;

/**
 * Something that have its own channel in chat.
 */
public interface Discussable {

    /**
     * @return chat channel where subject (eg. project, task) implementing this interface is discussed
     */
    Channel getChannel();
}
