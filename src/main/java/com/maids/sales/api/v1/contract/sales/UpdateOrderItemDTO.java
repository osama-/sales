package com.maids.sales.api.v1.contract.sales;

import java.util.List;

public class UpdateOrderItemDTO {
  private int id;
  private int quantity;
  private double price;

    public UpdateOrderItemDTO() {
    }

    public UpdateOrderItemDTO(int quantity, double price) {
        this.quantity = quantity;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "UpdateOrderItemDTO{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
