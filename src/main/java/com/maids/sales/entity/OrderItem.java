package com.maids.sales.entity;

import javax.persistence.*;

@Entity
@Table(name = "order_item")
public class OrderItem {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

   @Basic
   @Column(name = "quantity")
    private int quantity;

    @Basic
    @Column(name = "price")
    private double price;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,
            CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "order_id", referencedColumnName = "id", nullable = false)
    private Order order;




    @ManyToOne(fetch = FetchType.EAGER,cascade = {CascadeType.DETACH,CascadeType.MERGE,
            CascadeType.PERSIST,CascadeType.REFRESH} )
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
    private Product product;

    public OrderItem() {
    }

    public OrderItem(int quantity, double price) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderItem orderItem = (OrderItem) o;

        return  (id == orderItem.id)  ;

    }

    @Override
    public int hashCode() {
        int result = id;
        //result = 31 * result + orderId;
       // result = 31 * result + productId;
       // result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
        //result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }


    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }


    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
