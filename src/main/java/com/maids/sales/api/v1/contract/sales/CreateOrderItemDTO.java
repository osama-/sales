package com.maids.sales.api.v1.contract.sales;

public class CreateOrderItemDTO {
    private int productId;
    private int quantity;
    private double price;

    public CreateOrderItemDTO() {
    }

    public CreateOrderItemDTO(int productId, int quantity, double price) {
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int product_id) {
        this.productId = product_id;
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
        return "CreateOrderItemDTO{" +
                "product_id=" + productId +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
