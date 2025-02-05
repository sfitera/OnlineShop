package org.dreamteam.onlineshop.service;

import org.dreamteam.onlineshop.model.DTOs.UserDTO;
import org.dreamteam.onlineshop.model.User;

import java.util.List;

public interface UserService {

    void addUser(UserDTO userDTO);

    void updateUser(Long id, UserDTO userDTO);

    void deleteUser(Long id);

    User getUser(Long id);

    List<User> getUsers();
}
