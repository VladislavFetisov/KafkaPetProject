package com.example.consumer.repository;

import com.example.consumer.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
    private final JdbcTemplate template;

    @Autowired
    public UserRepository(JdbcTemplate template) {
        this.template = template;
    }

    public void saveUser(User user) {
        template.update("insert into users_1(login, password, name, lastname) values (?, ?, ?, ?)",
                user.getLogin(), user.getPassword(), user.getName(), user.getLastName());
    }
}
