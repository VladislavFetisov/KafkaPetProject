package com.example.consumer.service;

import com.example.consumer.model.User;
import com.example.consumer.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @KafkaListener(topics = "users", groupId = "foo")
    public void processUser(User user) {
        logger.info(user.toString());
        repository.saveUser(user);
    }

    @KafkaListener(topics = "users", groupId = "bar")
    public void processOtherGroup(User user) {
        logger.info(user.toString());
        logger.info("users from other groups");
        repository.saveUser(user);
    }
}
