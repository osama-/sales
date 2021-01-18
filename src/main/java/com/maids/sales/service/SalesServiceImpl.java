package com.maids.sales.service;

import com.maids.sales.api.v1.contract.sales.CreateOrderDTO;
import com.maids.sales.api.v1.contract.sales.OrderResultDTO;
import com.maids.sales.api.v1.contract.sales.UpdateOrderDTO;
import com.maids.sales.api.v1.mapper.OrderMapper;
import com.maids.sales.dao.OrderItemJPARepository;
import com.maids.sales.dao.OrderJPARepository;
import com.maids.sales.entity.Client;
import com.maids.sales.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SalesServiceImpl implements SalesService {
    private OrderJPARepository orderJPARepository;

    private final OrderMapper orderMapper;

    @Autowired
    public SalesServiceImpl(OrderJPARepository orderJPARepository, OrderMapper orderMapper) {
        this.orderJPARepository = orderJPARepository;
        this.orderMapper = orderMapper;
    }

    @Override
    public List<OrderResultDTO> findAll() {
        return orderJPARepository.findAll()
                .stream()
                .map(orderMapper::orderToOrderResultDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Order findById(int id) {
        Optional<Order> result = this.orderJPARepository.findById(id);
        Order order = null;
        if(result.isPresent())
            order = result.get();
        else
            throw new RuntimeException("Did not find client id -" + id);
return  order;

    }

    @Override
    public OrderResultDTO saveOrder(CreateOrderDTO order) {

        return this.orderJPARepository.saveOrder(order);

    }

    @Override
    public OrderResultDTO updateOrder(UpdateOrderDTO order) {
        return this.orderJPARepository.updateOrder(order);
    }
}
