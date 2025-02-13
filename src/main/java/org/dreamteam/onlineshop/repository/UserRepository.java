package org.dreamteam.onlineshop.repository;

import org.dreamteam.onlineshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByUsername(String username);
    Optional<User> findUserByUserEmail(String email);
}
