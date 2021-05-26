package com.testing.demo.model.service;

import com.testing.demo.model.entity.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findById(int id);

    User getByEmailAndPassword(String email, String password);

    Optional<User> findByEmail(String email);

    User save(User entity);

    void setNewParameterUser (User user, String userName, String userSurname, String userEmail,String password, String userStatus);
}
