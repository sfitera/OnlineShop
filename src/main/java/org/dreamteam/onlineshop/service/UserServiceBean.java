package org.dreamteam.onlineshop.service;

import org.dreamteam.onlineshop.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserServiceBean  implements UserService {

    private Map<Long, User> userDatabase = new HashMap<>();
    private long idCounter = 1;

    @Override
    public User createUser(User user) {
        user.setId(idCounter++);
        userDatabase.put(user.getId(), user);
        return user;
    }

    @Override
    public User updateUser(User user) {
        return null;
    }

    @Override
    public User deleteUser(Long id) {
        return null;
    }

    @Override
    public User getUser(Long id) {
        return null;
    }

    @Override
    public List<User> getUsers() {
        return List.of();
    }

}