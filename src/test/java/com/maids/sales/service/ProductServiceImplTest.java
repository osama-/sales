package com.maids.sales.service;

import com.maids.sales.api.v1.contract.client.ClientDTO;
import com.maids.sales.api.v1.mapper.ClientMapper;
import com.maids.sales.api.v1.mapper.ProductMapper;
import com.maids.sales.dao.ClientJPARepository;
import com.maids.sales.dao.ProductJPARepository;
import com.maids.sales.entity.Client;
import com.maids.sales.entity.Product;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestPropertySource(locations="classpath:application–test.properties")
class ProductServiceImplTest {
    @Autowired
    ProductJPARepository productJPARepository;
    ProductMapper productMapper= ProductMapper.INSTANCE;
    @Autowired
    ProductService productService;

    @Autowired
    EntityManager entityManger;


    @Test
    @Transactional
    void findAll() {
        try{
            entityManger.createQuery("DELETE FROM OrderItem").executeUpdate();
            entityManger.createQuery("DELETE FROM Order").executeUpdate();
            entityManger.createQuery("DELETE FROM Product ").executeUpdate();
            entityManger.createQuery("DELETE FROM Client ").executeUpdate();
        }catch (Exception ex){

        }
        Product product = new Product();
        product.setName("phone");
        product.setDescription("5inch screen");
        product.setCreationDate(LocalDateTime.now());
        product.setCategory("mobiles and accessories");
        productService.save(product);
        productService.save(product);
        List<Product> l= productService.findAll();
        assertEquals( 1,productService.findAll().size() );
    }

    @Test
    void save() {
        Product product = new Product();
        product.setName("phone");
        product.setDescription("5inch screen");
        product.setCreationDate(LocalDateTime.now());
        product.setCategory("mobiles and accessories");
        productService.save(product);
        Product p =productService.findById(product.getId());
        assertAll(

                ()->assertEquals(p.getName(),product.getName()),
                ()->assertEquals(p.getDescription(),product.getDescription()),
                ()->assertEquals(p.getCategory(),product.getCategory()),
                ()->assertEquals(p.getId(),product.getId())

        );
    }
}