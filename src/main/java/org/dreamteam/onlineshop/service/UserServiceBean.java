package org.dreamteam.onlineshop.service;

import lombok.extern.slf4j.Slf4j;
import org.dreamteam.onlineshop.mapper.EntityMapper;
import org.dreamteam.onlineshop.model.DTOs.UserDTO;
import org.dreamteam.onlineshop.model.User;
import org.dreamteam.onlineshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service

public class UserServiceBean implements UserService {

    private final UserRepository userRepository;
    private final EntityMapper entityMapper;

    @Autowired
    public UserServiceBean(UserRepository userRepository, EntityMapper entityMapper) {
        this.userRepository = userRepository;
        this.entityMapper = entityMapper;
    }

    @Override
    public void addUser(UserDTO userDTO) {
        User user = entityMapper.toUserEntity(userDTO);
        userRepository.save(user);
    }

    @Override
    public void updateUser(Long id, UserDTO userDTO) {
        var existingUser = userRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User with id " + id + " does not exist"));

        User updateUser = entityMapper.toUserEntity(userDTO);

        if (updateUser.getUserName() != null && !updateUser.getUserName().isBlank()) {
            existingUser.setUserName(updateUser.getUserName());
        }
        if (updateUser.getUserPassword() != null && !updateUser.getUserPassword().isBlank()) {
            existingUser.setUserPassword(updateUser.getUserPassword());
        }
        if (updateUser.getUserAddress() != null && !updateUser.getUserAddress().isBlank()) {
            existingUser.setUserAddress(updateUser.getUserAddress());
        }
        if (updateUser.getUserEmail() != null && !updateUser.getUserEmail().isBlank()) {
            existingUser.setUserEmail(updateUser.getUserEmail());
        }
        if (updateUser.getUserRole() != null) {
            existingUser.setUserRole(updateUser.getUserRole());
        }

        userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("User with id" + id + "does not exist");
        }
        userRepository.deleteById(id);
    }

    @Override
    public User getUser(Long id) {
        return userRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User with id " + id + " does not exist"));
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

}