package org.dreamteam.onlineshop.service;

import org.dreamteam.onlineshop.model.User;
import org.dreamteam.onlineshop.model.enums.UserRole;

import java.util.List;

public interface UserService {

 User createUser( String userName, String userPassword, String userAddress, String userEmail, UserRole userRole);
    User updateUser(Long id,User updateUser);
    void deleteUser(Long id);
    User getUser(Long id);

    List<User> getUsers();
}
