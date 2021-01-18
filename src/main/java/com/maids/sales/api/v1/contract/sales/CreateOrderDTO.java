package com.maids.sales.api.v1.contract.sales;

import java.util.List;

public class CreateOrderDTO {
    private Integer clientId;
    private String seller;
    private List<CreateOrderItemDTO> orderItems;

    public CreateOrderDTO() {
    }

    public CreateOrderDTO(int clientId, String seller, List<CreateOrderItemDTO> orderItems) {
        this.clientId = clientId;
        this.seller = seller;
        this.orderItems = orderItems;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public List<CreateOrderItemDTO> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<CreateOrderItemDTO> order_items) {
        this.orderItems = order_items;
    }

    @Override
    public String toString() {
        return "CreateOrderDTO{" +
                "client_id=" + clientId +
                ", seller='" + seller + '\'' +
                ", order_items=" + orderItems +
                '}';
    }
}
