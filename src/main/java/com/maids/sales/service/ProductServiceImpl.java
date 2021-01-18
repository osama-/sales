package com.maids.sales.service;


import com.maids.sales.api.v1.mapper.ProductMapper;
import com.maids.sales.dao.ProductJPARepository;
import com.maids.sales.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductJPARepository productJpaRepository;
    private final ProductMapper productMapper;
    @Autowired
    public ProductServiceImpl(ProductJPARepository productJpaRepository, ProductMapper productMapper) {
        this.productJpaRepository = productJpaRepository;
        this.productMapper = productMapper;
    }

    @Override
    public List<Product> findAll() {
        return  productJpaRepository.findAll();

    }

    @Override
    public Product findById(int id) {
        Optional<Product> result = this.productJpaRepository.findById(id);
        Product product = null;
        if(result.isPresent())
            product = result.get();
        else
            throw new RuntimeException("Did not find product id -" + id);
        return product;

    }

    @Override
    public void save(Product product) {

            this.productJpaRepository.save(product);
    }


}
