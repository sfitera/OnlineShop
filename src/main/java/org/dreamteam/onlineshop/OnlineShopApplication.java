package org.dreamteam.onlineshop;

import org.dreamteam.onlineshop.model.*;
import org.dreamteam.onlineshop.model.enums.Category;
import org.dreamteam.onlineshop.model.enums.OrderStatus;
import org.dreamteam.onlineshop.model.enums.UserRole;
import org.dreamteam.onlineshop.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableJpaRepositories("org.dreamteam.onlineshop")
public class OnlineShopApplication implements CommandLineRunner {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public OnlineShopApplication(UserRepository userRepository, ProductRepository productRepository,
                                 OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;

    }

    public static void main(String[] args) {
        SpringApplication.run(OnlineShopApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        List<User> users = new ArrayList<>(List.of(
                new User("Janko", "Hruska", "Ruzova 1", "janko@gmail.com", UserRole.USER),
                new User("Janko", "Hruska", "Ruzova 1", "janko@gmail.com", UserRole.USER),
                new User("Janko", "Hruska", "Ruzova 1", "janko@gmail.com", UserRole.USER),
                new User("Janko", "Hruska", "Ruzova 1", "janko@gmail.com", UserRole.USER),
                new User("Janko", "Hruska", "Ruzova 1", "janko@gmail.com", UserRole.USER)
        ));


        List<Product> products = new ArrayList<>(List.of(
                new Product("Kniha", 15.00, 2, "img", Category.BIOGRAPHY, "Ferko", "Olala"),
                new Product("Kniha2", 100.00, 0, "img", Category.BIOGRAPHY, "Ferko", "Olala"),
                new Product("Kniha3", 35.00, 2,  "img", Category.BIOGRAPHY, "Ferko", "Olala"),
                new Product("Kniha4", 45.00, 4, "img", Category.BIOGRAPHY, "Ferko", "Olala"),
                new Product("Kniha5", 20.00, 8, "img", Category.BIOGRAPHY, "Ferko", "Olala")
        ));

        Order order = new Order(users.getFirst(), OrderStatus.ORDERED);
        Order order2 = new Order(users.getFirst(), OrderStatus.CANCELLED);
        Order order3 = new Order(users.getFirst(), OrderStatus.DELIVERED);


        userRepository.saveAll(users);
        productRepository.saveAll(products);
        orderRepository.save(order);
        orderRepository.save(order2);
        orderRepository.save(order3);

        OrderItem orderItem1 = new OrderItem(products.get(0), 3);
        OrderItem orderItem2 = new OrderItem(products.get(1), 1);

        order.addOrderItem(orderItem1);
        order.addOrderItem(orderItem2);

        orderRepository.save(order);

    }

}