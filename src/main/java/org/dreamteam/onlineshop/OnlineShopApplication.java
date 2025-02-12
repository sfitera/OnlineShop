package org.dreamteam.onlineshop;

import org.dreamteam.onlineshop.model.*;
import org.dreamteam.onlineshop.model.enums.Category;
import org.dreamteam.onlineshop.model.enums.OrderStatus;
import org.dreamteam.onlineshop.model.enums.UserRole;
import org.dreamteam.onlineshop.repository.*;
import org.dreamteam.onlineshop.service.DatabaseInitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableJpaRepositories("org.dreamteam.onlineshop")
public class OnlineShopApplication implements CommandLineRunner {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final DatabaseInitService databaseInitService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public OnlineShopApplication(UserRepository userRepository, ProductRepository productRepository,
                                 OrderRepository orderRepository, DatabaseInitService databaseInitService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;

        this.databaseInitService = databaseInitService;
        this.passwordEncoder = passwordEncoder;
    }

    public static void main(String[] args) {
        SpringApplication.run(OnlineShopApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        //databaseInitService.initializeDatabase();
//        User user = new User("admin", passwordEncoder.encode("admin123"), "admin address", "admin@email.com");
//        user.setUserRole(UserRole.ADMIN);
//        userRepository.save(user);


    }

}