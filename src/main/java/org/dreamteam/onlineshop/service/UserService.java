package org.dreamteam.onlineshop.service;

import org.dreamteam.onlineshop.model.User;

import java.util.List;

public interface UserService {

    void addUser(User user);

    void updateUser(Long id, User updateUser);

    void deleteUser(Long id);

    User getUser(Long id);

    List<User> getUsers();
}
