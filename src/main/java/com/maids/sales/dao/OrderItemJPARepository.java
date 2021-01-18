package com.maids.sales.dao;

import com.maids.sales.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemJPARepository extends JpaRepository<OrderItem,Integer> {
    @Modifying
    @Query("UPDATE OrderItem u SET u.quantity = :quantity, u.price = :price  WHERE u.id = :id")
    void updateOrderItem(@Param("id") int id, @Param("quantity") int quantity, @Param("price") double price);
}
