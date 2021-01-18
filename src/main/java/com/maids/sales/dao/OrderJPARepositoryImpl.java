package com.maids.sales.dao;

import com.maids.sales.api.v1.contract.sales.*;
import com.maids.sales.api.v1.mapper.OrderMapper;
import com.maids.sales.entity.Client;
import com.maids.sales.entity.Order;
import com.maids.sales.entity.OrderItem;
import com.maids.sales.entity.Product;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

public class OrderJPARepositoryImpl implements CustomOrderRepository {
    private EntityManager entityManger;
    private final OrderMapper orderMapper;
    private OrderItemJPARepository orderItemJPARepository;
    Logger logger = LoggerFactory.getLogger(OrderJPARepositoryImpl.class);

    @Autowired
    public OrderJPARepositoryImpl(EntityManager entityManger, OrderMapper orderMapper, OrderItemJPARepository orderItemJPARepository) {
        this.entityManger = entityManger;
        this.orderMapper = orderMapper;
        this.orderItemJPARepository = orderItemJPARepository;
    }


    @Override
    @Transactional
    public OrderResultDTO saveOrder(CreateOrderDTO createOrderDTO) {
        Order order = new Order(createOrderDTO.getSeller());
        order.setClient(entityManger.getReference(Client.class, createOrderDTO.getClientId()));
        for (CreateOrderItemDTO createOrderItemDTO : createOrderDTO.getOrderItems()) {
            OrderItem orderItem = new OrderItem(createOrderItemDTO.getQuantity(), createOrderItemDTO.getPrice());

            orderItem.setProduct(entityManger.getReference(Product.class, createOrderItemDTO.getProductId()));
            order.add(orderItem);
        }
        entityManger.persist(order);
        return orderMapper.orderToOrderResultDTO(order);

    }

    @Override
    @Transactional
    public OrderResultDTO updateOrder(UpdateOrderDTO updateOrderDTO) {
        logger.info("Start updating order id:" + updateOrderDTO.getId());
        for (UpdateOrderItemDTO oi : updateOrderDTO.getOrderItems()) {
            orderItemJPARepository.updateOrderItem(oi.getId(), oi.getQuantity(), oi.getPrice());
            logger.info("Update item id: " + oi.getId() + " new qty: " + oi.getQuantity() + " new price: " + oi.getPrice());
        }

        Order order = entityManger.find(Order.class, updateOrderDTO.getId());
        logger.info("Done updating order id:" + updateOrderDTO.getId());
        return orderMapper.orderToOrderResultDTO(order);
    }
}
