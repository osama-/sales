package com.maids.sales.controllers.v1;

import com.maids.sales.entity.Product;
import com.maids.sales.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping("/products")
    public List<Product> findAll(){

        return this.productService.findAll();
    }

    @GetMapping("/products/{productId}")
    public Product getProduct(@PathVariable int productId){
        Product jobType = this.productService.findById(productId);
        if(jobType == null) {
            throw new RuntimeException("Job type id not found- " +productId);
        }
        return jobType;
    }
    @PostMapping("/products")
    public Product add(@RequestBody Product product) {

        product.setId(0);
        product.setCreationDate(LocalDateTime.now());
        this.productService.save(product);
        return product;
    }
    @PutMapping("/products")
    public Product updateProduct(@RequestBody Product product) {

        this.productService.save(product);
        return product;

    }
}
