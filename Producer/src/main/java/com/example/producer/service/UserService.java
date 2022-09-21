package com.example.producer.service;

import com.example.producer.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.ExecutionException;

@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final KafkaTemplate<String, User> kafkaTemplate;

    @Autowired
    public UserService(KafkaTemplate<String, User> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendUser(User user) {
        ListenableFuture<SendResult<String, User>> future = kafkaTemplate.send("users", user.getLogin(), user);
        try {
            SendResult<String, User> stringUserSendResult = future.get();
            logger.info(stringUserSendResult.toString());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
