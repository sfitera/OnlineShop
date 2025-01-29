package org.dreamteam.onlineshop.service;

import lombok.extern.slf4j.Slf4j;
import org.dreamteam.onlineshop.model.User;

import org.dreamteam.onlineshop.model.enums.UserRole;
import org.dreamteam.onlineshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;


@Slf4j
@Service

public class UserServiceBean  implements UserService {

private final UserRepository userRepository;

    @Autowired
    public UserServiceBean(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User addUser( String userName, String userPassword, String userAddress, String userEmail, UserRole userRole) {
        User user = new User(userName,userPassword,userAddress,userEmail, userRole);
        userRepository.save(user);
        return user;
    }

    @Override
    public User updateUser (Long id, User updateUser ) {
        var user = userRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User with id " + id + " does not exist"));
        var updated = new User();
        if (StringUtils.hasText(updateUser.getUserName())) {
            updated.setUserName(updateUser.getUserName());
        } else {
            updated.setUserName(user.getUserName());
        }
        if (StringUtils.hasText(updateUser.getUserPassword())) {
            updated.setUserPassword(updateUser.getUserPassword());
        }
        else {
            updated.setUserPassword(user.getUserPassword());
        }
        if (StringUtils.hasText(updateUser.getUserAddress())) {
            updated.setUserAddress(updateUser.getUserAddress());
        }
        else {
            updated.setUserAddress(user.getUserAddress());
        }
        if (StringUtils.hasText(updateUser.getUserEmail())) {
            updated.setUserEmail(updateUser.getUserEmail());
        }
        else {
            updated.setUserEmail(user.getUserEmail());
        }
        if (StringUtils.hasText(String.valueOf(updateUser.getUserRole()))) {
            updated.setUserRole(updateUser.getUserRole());
        }
        else {
            updated.setUserRole(user.getUserRole());
        }
       userRepository.save(updated);
        log.info("User updated: {}", updated);
        return updated;
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
             .orElseThrow(() -> new IllegalArgumentException("User with id" + id + "does not exist"));
    }

    @Override
    public List<User> getUsers() {return userRepository.findAll();
    }

}