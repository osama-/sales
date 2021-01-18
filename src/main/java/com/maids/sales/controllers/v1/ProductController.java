package com.maids.sales.controllers.v1;

import com.maids.sales.api.v1.contract.product.ProductDTO;
import com.maids.sales.api.v1.mapper.ProductMapper;
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
    private final ProductMapper productMapper;
    @Autowired
    public ProductController(ProductService productService,ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }
    @GetMapping("/products")
    public List<Product> findAll(){

        return this.productService.findAll();
    }

    @GetMapping("/products/{productId}")
    public Product getProduct(@PathVariable int productId){
        Product product = this.productService.findById(productId);
        if(product == null) {
            throw new RuntimeException("Job type id not found- " +productId);
        }
        return product;
    }
    @PostMapping("/products")
    public ProductDTO add(@RequestBody ProductDTO productDTO) {
        Product product = productMapper.productDTOToProduct(productDTO);
        product.setId(0);
        product.setCreationDate(LocalDateTime.now());
        this.productService.save(product);
        return productMapper.productToProductDTO(product);
    }
    @PutMapping("/products")
    public ProductDTO updateProduct(@RequestBody ProductDTO productDTO) {
        Product product =productMapper.productDTOToProduct(productDTO);
        this.productService.save(product);
        return productMapper.productToProductDTO(product);

    }
}
