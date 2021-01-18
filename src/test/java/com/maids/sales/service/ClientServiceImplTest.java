package com.maids.sales.service;

import com.maids.sales.SalesApplication;
import com.maids.sales.api.v1.contract.client.ClientDTO;
import com.maids.sales.api.v1.contract.sales.OrderResultDTO;
import com.maids.sales.api.v1.mapper.ClientMapper;
import com.maids.sales.config.H2TestProfileJPAConfig;
import com.maids.sales.dao.ClientJPARepository;
import com.maids.sales.entity.Client;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestPropertySource(locations="classpath:application–test.properties")
//@SpringBootTest(classes = {
//        H2TestProfileJPAConfig.class})

//@ContextConfiguration(classes=H2TestProfileJPAConfig.class, loader= AnnotationConfigContextLoader.class)

class ClientServiceImplTest {

    @Autowired

    ClientMapper clientMapper= ClientMapper.INSTANCE;
    @Autowired
    ClientService clientService;

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
        Client client = new Client();
        client.setName("Ossama");
        client.setLastName("Ismaeel");
        client.setMobile("81737519");
        clientService.save(client);
        List<ClientDTO> l= clientService.findAll();
       assertEquals( 1,clientService.findAll().size() );

    }

    @Test
    void save() {
        Client client = new Client();
        client.setName("Ossama");
        client.setLastName("Ismaeel");
        client.setMobile("81737519");
        clientService.save(client);
         ClientDTO clientDTO =clientService.findById(client.getId());
        assertAll(

                        ()->assertEquals(clientDTO.getName(),client.getName()),
                        ()->assertEquals(clientDTO.getLastName(),client.getLastName()),
                        ()->assertEquals(clientDTO.getMobile(),client.getMobile()),
                         ()->assertEquals(clientDTO.getId(),client.getId())

                );


    }
}