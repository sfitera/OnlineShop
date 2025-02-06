package org.dreamteam.onlineshop.service;

import org.dreamteam.onlineshop.model.Order;
import org.dreamteam.onlineshop.model.OrderItem;
import org.dreamteam.onlineshop.model.enums.OrderStatus;
import org.dreamteam.onlineshop.repository.OrderRepository;
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

    @InjectMocks
    private OrderServiceBean orderService;

    private Order testOrder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        testOrder = new Order();
        testOrder.setId(1L);
        testOrder.setOrderStatus(OrderStatus.CREATED);
        testOrder.setOrderItems(Collections.emptyList());
    }

    @Test
    void testCreateOrder() {
        orderService.createOrder(testOrder);
        verify(orderRepository, times(1)).save(testOrder);
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

        assertEquals("Order not found", exception.getMessage(), "Exception message should indicate order was not found");
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

        assertEquals("Order not found", exception.getMessage(), "Exception message should indicate order was not found");
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

        assertEquals("Order not found", exception.getMessage(), "Exception message should indicate order was not found");
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

    @Test
    void testAddOrderItem() {
        OrderItem newItem = new OrderItem();
        newItem.setId(100L);

        testOrder.setOrderItems(new ArrayList<>());

        when(orderRepository.findById(1L)).thenReturn(Optional.of(testOrder));

        orderService.addOrderItem(1L, newItem);

        assertEquals(1, testOrder.getOrderItems().size(), "Order should contain exactly one item");
        assertTrue(testOrder.getOrderItems().contains(newItem), "Order should contain the added item");
        verify(orderRepository, times(1)).save(testOrder);
    }

    @Test
    void testDeleteOrderItem() {
        OrderItem item1 = new OrderItem();
        item1.setId(100L);
        OrderItem item2 = new OrderItem();
        item2.setId(200L);

        testOrder.setOrderItems(new ArrayList<>(Arrays.asList(item1, item2)));

        when(orderRepository.findById(1L)).thenReturn(Optional.of(testOrder));

        orderService.deleteOrderItem(1L, 100L);

        assertEquals(1, testOrder.getOrderItems().size(), "Only one item should remain after deletion");
        assertFalse(testOrder.getOrderItems().contains(item1), "Deleted item should not be in the order anymore");
        verify(orderRepository, times(1)).save(testOrder);
    }
}