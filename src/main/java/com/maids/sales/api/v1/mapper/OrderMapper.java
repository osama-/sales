package com.maids.sales.api.v1.mapper;

import com.maids.sales.api.v1.contract.sales.*;
import com.maids.sales.entity.Order;
import com.maids.sales.entity.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);
    @Mapping(source = "order.client.name", target = "client")
    @Mapping(source = "order.orderItems", target = "orderItems")
    OrderResultDTO orderToOrderResultDTO (Order order);
    CreateOrderDTO OrderToOrderDTO(Order order);

    Order OrderDTOToOrder(CreateOrderDTO createOrderDTO);
    @Mapping(source = "orderItem.product.name", target = "product")
    OrderItemResultDTO orderItemToOrderItemResultDTO(OrderItem orderItem);

    UpdateOrderDTO OrderResultDtoToUpdateOrderDto (OrderResultDTO order);
    UpdateOrderItemDTO OrderItemResultDtoToUpdateOrderItemDto (OrderItemResultDTO order);



}
