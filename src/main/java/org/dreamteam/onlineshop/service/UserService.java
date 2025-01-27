package org.dreamteam.onlineshop.service;

import org.dreamteam.onlineshop.model.User;

import java.util.List;

public interface UserService {

    User createUser(User user);
    User updateUser(User user);
    User deleteUser(Long id);
    User getUser(Long id);
    List<User> getUsers();
}
