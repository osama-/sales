package com.maids.sales.dao;

import com.maids.sales.entity.Order;
import com.maids.sales.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderJPARepository extends JpaRepository<Order,Integer>,CustomOrderRepository {

}
