package com.substring.chat.repositories;

import java.time.LocalDateTime;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.substring.chat.entities.Message;

public interface MessageRepository extends MongoRepository<Message,LocalDateTime> {

    
} 