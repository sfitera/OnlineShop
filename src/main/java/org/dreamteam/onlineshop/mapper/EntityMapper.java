package org.dreamteam.onlineshop.mapper;

import org.dreamteam.onlineshop.model.DTOs.*;
import org.dreamteam.onlineshop.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EntityMapper {

    EntityMapper INSTANCE = Mappers.getMapper(EntityMapper.class);

    ProductDTO toProductDTO(Product product);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "productAvailability", expression = "java(productDTO.getProductQuantity() > 0)")
    Product toProductEntity(ProductDTO productDTO);

    UserDTO toUserDTO(User user);

    @Mapping(target = "id", ignore = true)
    User toUserEntity(UserDTO userDTO);

    @Mapping(target = "productId", source = "product.id")
    OrderItemDTO toOrderItemDTO(OrderItem orderItem);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "product", ignore = true)
    @Mapping(target = "order", ignore = true)
    @Mapping(target = "itemPrice", ignore = true)
    OrderItem toOrderItemEntity(OrderItemDTO orderItemDTO);

    @Mapping(target = "userId", source = "user.id")
    OrderDTO toOrderDTO(Order order);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "totalPrice", ignore = true)
    Order toOrderEntity(OrderDTO orderDTO);
}
