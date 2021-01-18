package com.maids.sales.api.v1.contract.sales;

import java.util.List;

public class UpdateOrderDTO {
    private int id;
    private List<UpdateOrderItemDTO> orderItems;

    public UpdateOrderDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<UpdateOrderItemDTO> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<UpdateOrderItemDTO> orderItems) {
        this.orderItems = orderItems;
    }

    @Override
    public String toString() {
        return "UpdateOrderDTO{" +
                "id=" + id +
                ", orderItems=" + orderItems +
                '}';
    }
}
