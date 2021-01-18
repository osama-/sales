package com.maids.sales.service;

import com.maids.sales.api.v1.contract.sales.CreateOrderDTO;
import com.maids.sales.api.v1.contract.sales.OrderResultDTO;
import com.maids.sales.api.v1.contract.sales.UpdateOrderDTO;
import com.maids.sales.entity.Order;

import java.util.List;

public interface SalesService {
     List<OrderResultDTO> findAll();
     Order findById(int id);

     OrderResultDTO saveOrder(CreateOrderDTO order);
     OrderResultDTO updateOrder(UpdateOrderDTO order);

}
