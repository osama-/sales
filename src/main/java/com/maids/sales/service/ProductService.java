package com.maids.sales.service;

import com.maids.sales.entity.Product;

import java.util.List;

public interface ProductService {
    public List<Product> findAll();
    public Product findById(int id);

    public void save(Product product);


}
