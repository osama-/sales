package com.maids.sales.service;

import com.maids.sales.api.v1.contract.client.ClientDTO;
import com.maids.sales.api.v1.contract.sales.CreateOrderDTO;
import com.maids.sales.api.v1.contract.sales.CreateOrderItemDTO;
import com.maids.sales.api.v1.contract.sales.OrderResultDTO;
import com.maids.sales.api.v1.contract.sales.UpdateOrderDTO;
import com.maids.sales.api.v1.mapper.ClientMapper;
import com.maids.sales.api.v1.mapper.OrderMapper;
import com.maids.sales.dao.ClientJPARepository;
import com.maids.sales.dao.OrderJPARepository;
import com.maids.sales.entity.Client;
import com.maids.sales.entity.Order;
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
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application–test.properties")

class SalesServiceImplTest {


    OrderMapper orderMapper = OrderMapper.INSTANCE;
    @Autowired
    SalesService salesService;
    @Autowired
    ClientService clientService;
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
        createOrder();
        List<ClientDTO> l = clientService.findAll();
        assertEquals(1, clientService.findAll().size());

    }

    @Test
    @Transactional
    void saveOrder() {

        OrderResultDTO orderResultDTO = createOrder();
        Order order = salesService.findById(orderResultDTO.getId());
        assertAll(

                () -> assertEquals(order.getClient().getName(), orderResultDTO.getClient()),
                () -> assertEquals(order.getSeller(), orderResultDTO.getSeller()),
                () -> assertEquals(order.getId(), orderResultDTO.getId()),
                () -> assertEquals(550, orderResultDTO.getTotal())


        );
    }

    @Test
    void updateOrder() {


        OrderResultDTO orderResultDTO= createOrder();
        UpdateOrderDTO updateOrderDTO = orderMapper.OrderResultDtoToUpdateOrderDto(orderResultDTO);
        updateOrderDTO.getOrderItems().get(0).setPrice(100);
        updateOrderDTO.getOrderItems().get(0).setQuantity(3);
        updateOrderDTO.getOrderItems().get(1).setPrice(75);
        updateOrderDTO.getOrderItems().get(1).setQuantity(10);
        OrderResultDTO updatedOrderResultDto= salesService.updateOrder(updateOrderDTO);
        assertAll(

                () -> assertEquals(1050, updatedOrderResultDto.getTotal()),
                () -> assertEquals("Ossama", updatedOrderResultDto.getClient()),
                () -> assertEquals("amazon", updatedOrderResultDto.getSeller())


        );
    }

    public OrderResultDTO createOrder(){
        Client client = new Client();
        client.setName("Ossama");
        client.setLastName("Ismaeel");
        client.setMobile("81737519");
        clientService.save(client);

        Product product = new Product();
        product.setName("phone");
        product.setDescription("5inch screen");
        product.setCreationDate(LocalDateTime.now());
        product.setCategory("mobiles and accessories");
        productService.save(product);

        Product product2 = new Product();
        product2.setName("dell alienware r15");
        product2.setDescription("gaming laptop");
        product2.setCreationDate(LocalDateTime.now());
        product2.setCategory("laptops");
        productService.save(product2);

        CreateOrderDTO orderDTO = new CreateOrderDTO();
        orderDTO.setSeller("amazon");
        orderDTO.setClientId(client.getId());
        orderDTO.setOrderItems(new ArrayList<CreateOrderItemDTO>() {
                                   {
                                       add(new CreateOrderItemDTO(product.getId(), 2, 125));
                                       add(new CreateOrderItemDTO(product2.getId(), 3, 100));

                                   }
                               }
        );



        return salesService.saveOrder(orderDTO);
    }
}