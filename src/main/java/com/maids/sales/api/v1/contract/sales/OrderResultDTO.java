package com.maids.sales.api.v1.contract.sales;

import com.maids.sales.api.v1.contract.sales.OrderItemResultDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class OrderResultDTO {
    private  int id;
    private String client;
    private String seller;
    private double total;
    private LocalDateTime creationDate;
    private List<OrderItemResultDTO> orderItems;

    public OrderResultDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<OrderItemResultDTO> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemResultDTO> orderItems) {
        this.orderItems = orderItems;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "OrderResultDTO{" +
                "id=" + id +
                ", client='" + client + '\'' +
                ", seller='" + seller + '\'' +
                ", total=" + total +
                ", orderItems=" + orderItems +
                '}';
    }
}
