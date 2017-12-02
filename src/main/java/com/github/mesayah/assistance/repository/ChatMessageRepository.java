package com.github.mesayah.assistance.repository;

import com.github.mesayah.assistance.model.ChatMessage;
import org.springframework.data.repository.CrudRepository;

public interface ChatMessageRepository extends CrudRepository<ChatMessage, Long> {
}
