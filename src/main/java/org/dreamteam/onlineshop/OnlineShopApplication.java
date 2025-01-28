package org.dreamteam.onlineshop;

import org.dreamteam.onlineshop.model.*;
import org.dreamteam.onlineshop.model.enums.OrderStatus;
import org.dreamteam.onlineshop.model.enums.UserRole;
import org.dreamteam.onlineshop.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableJpaRepositories("org.dreamteam.onlineshop")
public class OnlineShopApplication implements CommandLineRunner {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public OnlineShopApplication(UserRepository userRepository, ProductRepository productRepository,
                                 OrderRepository orderRepository, OrderItemRepository orderItemRepository, CategoryRepository categoryRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.categoryRepository = categoryRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(OnlineShopApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        List<User> users = new ArrayList<>(List.of(
                new User( "Janko", "Hruska", "Ruzova 1", "janko@gmail.com", UserRole.USER),
                new User( "Janko", "Hruska", "Ruzova 1", "janko@gmail.com", UserRole.USER),
                new User( "Janko", "Hruska", "Ruzova 1", "janko@gmail.com", UserRole.USER),
                new User( "Janko", "Hruska", "Ruzova 1", "janko@gmail.com", UserRole.USER),
                new User( "Janko", "Hruska", "Ruzova 1", "janko@gmail.com", UserRole.USER)
        ));

        List<Category> categories = new ArrayList<>(List.of(
                new Category("Horor"),
                new Category("Romantika"),
                new Category("Komedia"),
                new Category("Naucna")
        ));

        List<Product> products = new ArrayList<>(List.of(
                new Product("Kniha", 15.00, 2, true, "img", categories.getFirst(), "Ferko", "Olala"),
                new Product( "Kniha2", 100.00, 0, false, "img", categories.getLast(), "Ferko", "Olala"),
                new Product( "Kniha3", 35.00, 2, true, "img", categories.getFirst(), "Ferko", "Olala"),
                new Product( "Kniha4", 45.00, 4, false, "img", categories.getLast(), "Ferko", "Olala"),
                new Product( "Kniha5", 20.00, 8, true, "img", categories.getFirst(), "Ferko", "Olala")
        ));

        List<OrderItem> orderItems = new ArrayList<>(List.of(
                new OrderItem(products.getFirst(), 5),
                new OrderItem(products.getFirst(), 5),
                new OrderItem(products.getLast(), 5)
        ));
        List<OrderItem> orderItems2 = new ArrayList<>(List.of(
                new OrderItem(products.getFirst(), 5),
                new OrderItem(products.getFirst(), 5),
                new OrderItem(products.getLast(), 5)
        ));
        List<OrderItem> orderItems3 = new ArrayList<>(List.of(
                new OrderItem(products.getFirst(), 5),
                new OrderItem(products.getFirst(), 5),
                new OrderItem(products.getLast(), 5)
        ));

        List<Order> orders = new ArrayList<>(List.of(
                new Order( users.getFirst(), OrderStatus.ORDERED, orderItems),
                new Order( users.getFirst(), OrderStatus.CANCELLED, orderItems2),
                new Order( users.getFirst(), OrderStatus.DELIVERED, orderItems3)
        ));

        userRepository.saveAll(users);
        categoryRepository.saveAll(categories);
        productRepository.saveAll(products);
        orderItemRepository.saveAll(orderItems);
        orderItemRepository.saveAll(orderItems2);
        orderItemRepository.saveAll(orderItems3);
        orderRepository.saveAll(orders);


    }

}
