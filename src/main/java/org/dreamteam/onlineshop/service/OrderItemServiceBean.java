package org.dreamteam.onlineshop.service;

import lombok.extern.slf4j.Slf4j;
import org.dreamteam.onlineshop.model.Order;
import org.dreamteam.onlineshop.model.OrderItem;
import org.dreamteam.onlineshop.model.Product;
import org.dreamteam.onlineshop.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class OrderItemServiceBean implements OrderItemService {

    private final OrderItemRepository orderItemRepository;

    @Autowired
    public OrderItemServiceBean(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }


    @Override
    public OrderItem addOrderItem(Product product, int quantity) {
        var orderItem = new OrderItem(product,quantity);
       orderItemRepository.save(orderItem);
       return orderItem;
    }

    @Override
    public OrderItem updateOrderItem(Long id,Order order, Product product,int quantity) {
        var orderItem = orderItemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Order item not found"));
                var updatedItem = new OrderItem();
                if(product!=null){
                    updatedItem.setProduct(product);
                } else {
                    updatedItem.setProduct(orderItem.getProduct());
                }
                if(quantity!=0){
                    updatedItem.setQuantity(quantity);
                } else {
                    updatedItem.setQuantity(orderItem.getQuantity());
                }
                orderItemRepository.save(updatedItem);

        return updatedItem;
    }

    @Override
    public void deleteOrderItem(Long orderItemId) {
        if(!orderItemRepository.existsById(orderItemId)){
           throw new IllegalArgumentException("Order item not found");
        }
        orderItemRepository.deleteById(orderItemId);
    }

    @Override
    public OrderItem getOrderItem(Long orderItemId) {
        return orderItemRepository.findById(orderItemId)
                .orElseThrow(() -> new IllegalArgumentException("Order item not found"));
    }

    @Override
    public List<OrderItem> getAllOrderItems() {
        return orderItemRepository.findAll();
    }
}
