package com.maids.sales.service;

import com.maids.sales.api.v1.contract.product.ProductDTO;
import com.maids.sales.entity.Product;

import java.util.List;

public interface ProductService {
    public List<Product> findAll();
    public Product findById(int id);

    public Product save(Product product);


}
