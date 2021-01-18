package com.maids.sales.dao;

import com.maids.sales.api.v1.contract.sales.CreateOrderDTO;
import com.maids.sales.api.v1.contract.sales.OrderResultDTO;
import com.maids.sales.api.v1.contract.sales.UpdateOrderDTO;

public interface CustomOrderRepository    {
     OrderResultDTO saveOrder(CreateOrderDTO order);
     OrderResultDTO updateOrder(UpdateOrderDTO order);
}
