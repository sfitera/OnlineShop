package org.dreamteam.onlineshop.bean;


import org.dreamteam.onlineshop.mapper.EntityMapper;
import org.dreamteam.onlineshop.model.DTOs.OrderDTO;
import org.dreamteam.onlineshop.model.Order;
import org.dreamteam.onlineshop.model.User;
import org.dreamteam.onlineshop.model.enums.OrderStatus;
import org.dreamteam.onlineshop.repository.OrderRepository;
import org.dreamteam.onlineshop.repository.UserRepository;
import org.dreamteam.onlineshop.service.OrderServiceBean;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderServiceBeanTest {

    @Mock
    private OrderRepository orderRepository;
    @Mock
    private UserRepository userRepository;

    @Mock
    private EntityMapper entityMapper;

    @InjectMocks
    private OrderServiceBean orderService;

    private OrderDTO testOrderDTO;
    private Order testOrder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        testOrderDTO = new OrderDTO();
        testOrderDTO.setUserId(1L);
        testOrderDTO.setOrderStatus(OrderStatus.CREATED);
        testOrderDTO.setOrderItems(new ArrayList<>());

        testOrder = new Order();
        testOrder.setId(1L);
        testOrder.setOrderStatus(OrderStatus.CREATED);

        User testUser = new User();
        testUser.setId(1L);
        testUser.setUserName("testUser");
        when(userRepository.findById(1L)).thenReturn(Optional.of(testUser));
    }

    @Test
    void testCreateOrder() {
        when(entityMapper.toOrderEntity(testOrderDTO)).thenReturn(testOrder);
        when(orderRepository.save(any(Order.class))).thenReturn(testOrder);

        Order result = orderService.createOrder(testOrderDTO);
        assertNotNull(result, "Order should be created successfully");
        verify(orderRepository, times(1)).save(any(Order.class));
    }

    @Test
    void testUpdateOrderStatus_Success() {
        when(orderRepository.findById(1L)).thenReturn(Optional.of(testOrder));

        orderService.updateOrderStatus(1L, OrderStatus.SHIPPED);

        assertEquals(OrderStatus.SHIPPED, testOrder.getOrderStatus(), "Order status should be updated to SHIPPED");
        verify(orderRepository, times(1)).save(testOrder);
    }

    @Test
    void testUpdateOrderStatus_OrderNotFound() {
        when(orderRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                orderService.updateOrderStatus(1L, OrderStatus.SHIPPED));

        assertEquals("Objednavka neexistuje", exception.getMessage(), "Exception message should indicate order was not found");
        verify(orderRepository, never()).save(any());
    }

    @Test
    void testDeleteOrder_Success() {
        when(orderRepository.findById(1L)).thenReturn(Optional.of(testOrder));

        orderService.deleteOrder(1L);

        verify(orderRepository, times(1)).delete(testOrder);
    }

    @Test
    void testDeleteOrder_NotFound() {
        when(orderRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                orderService.deleteOrder(1L));

        assertEquals("Objednavka neexistuje", exception.getMessage(), "Exception message should indicate order was not found");
        verify(orderRepository, never()).delete(any());
    }

    @Test
    void testGetOrderById_Success() {
        when(orderRepository.findById(1L)).thenReturn(Optional.of(testOrder));

        Order order = orderService.getOrderById(1L);

        assertNotNull(order, "Returned order should not be null");
        assertEquals(1L, order.getId(), "Order ID should match the expected value");
    }

    @Test
    void testGetOrderById_NotFound() {
        when(orderRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                orderService.getOrderById(1L));

        assertEquals("Objednavka neexistuje", exception.getMessage(), "Exception message should indicate order was not found");
    }

    @Test
    void testGetAllOrders() {
        List<Order> orders = Arrays.asList(testOrder, new Order());
        when(orderRepository.findAll()).thenReturn(orders);

        List<Order> result = orderService.getAllOrders();

        assertEquals(2, result.size(), "There should be exactly 2 orders in the result");
        verify(orderRepository, times(1)).findAll();
    }

    @Test
    void testGetOrdersByUserId() {
        List<Order> orders = List.of(testOrder);
        when(orderRepository.findAllByUserId(1L)).thenReturn(orders);

        List<Order> result = orderService.getOrdersByUserId(1L);

        assertEquals(1, result.size(), "There should be exactly 1 order for the given user ID");
        verify(orderRepository, times(1)).findAllByUserId(1L);
    }
}