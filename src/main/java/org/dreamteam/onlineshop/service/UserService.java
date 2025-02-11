package org.dreamteam.onlineshop.service;

import org.dreamteam.onlineshop.model.DTOs.UserDTO;
import org.dreamteam.onlineshop.model.DTOs.UserResponseDTO;


import java.util.List;


public interface UserService {
    void addUser(UserDTO userDTO);
    void updateUser(Long id, UserDTO userDTO);
    void deleteUser(Long id);
    UserResponseDTO getUser(Long id);
    List<UserResponseDTO> getUsers();
}
