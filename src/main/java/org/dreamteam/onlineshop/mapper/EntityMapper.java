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
    Product toProductEntity(ProductDTO productDTO);

    UserDTO toUserDTO(User user);

    @Mapping(target = "id", ignore = true)
    User toUserEntity(UserDTO userDTO);

    OrderItemDTO toOrderItemDTO(OrderItem orderItem);

    @Mapping(target = "id", ignore = true)
    OrderItem toOrderItemEntity(OrderItemDTO orderItemDTO);

    OrderDTO toOrderDTO(Order order);

    @Mapping(target = "id", ignore = true)
    Order toOrderEntity(OrderDTO orderDTO);
}
