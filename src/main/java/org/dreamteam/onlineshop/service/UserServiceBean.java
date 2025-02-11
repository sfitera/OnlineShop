package org.dreamteam.onlineshop.service;

import lombok.extern.slf4j.Slf4j;
import org.dreamteam.onlineshop.mapper.EntityMapper;
import org.dreamteam.onlineshop.model.DTOs.UserDTO;
import org.dreamteam.onlineshop.model.DTOs.UserResponseDTO;
import org.dreamteam.onlineshop.model.User;
import org.dreamteam.onlineshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserServiceBean implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final EntityMapper entityMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceBean(UserRepository userRepository, EntityMapper entityMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.entityMapper = entityMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void addUser(UserDTO userDTO) {
        User user = entityMapper.toUserEntity(userDTO);
        user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
        userRepository.save(user);
    }

    @Override
    public void updateUser(Long id, UserDTO userDTO) {
        var existingUser = userRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User with id " + id + " does not exist"));

        User updateUser = entityMapper.toUserEntity(userDTO);

        if (updateUser.getUsername() != null && !updateUser.getUsername().isBlank()) {
            existingUser.setUserName(updateUser.getUsername());
        }
        if (updateUser.getUserPassword() != null && !updateUser.getUserPassword().isBlank()) {
            existingUser.setUserPassword(passwordEncoder.encode(updateUser.getUserPassword()));
        }
        if (updateUser.getUserAddress() != null && !updateUser.getUserAddress().isBlank()) {
            existingUser.setUserAddress(updateUser.getUserAddress());
        }
        if (updateUser.getUserEmail() != null && !updateUser.getUserEmail().isBlank()) {
            existingUser.setUserEmail(updateUser.getUserEmail());
        }
        if (updateUser.getUserRoles() != null) {
            existingUser.setUserRoles(updateUser.getUserRoles());
        }

        userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("User with id " + id + " does not exist");
        }
        userRepository.deleteById(id);
    }

    @Override
    public UserResponseDTO getUser(Long id) {
        User user = userRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User with id " + id + " does not exist"));
        return entityMapper.toUserResponseDTO(user);
    }

    @Override
    public List<UserResponseDTO> getUsers() {
        List<User> users = userRepository.findAll();
        List<UserResponseDTO> userResponseDTOS = new ArrayList<>();
        for (User user : users) {
            userResponseDTOS.add(entityMapper.toUserResponseDTO(user));
        }
        return userResponseDTOS;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findUserByUserName(username);
        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException("Username " + username + " does not exist");
        } else {
            return userOptional.get();
        }
    }
}
