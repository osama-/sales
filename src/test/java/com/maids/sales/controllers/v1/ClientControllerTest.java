package com.maids.sales.controllers.v1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    EntityManager entityManger;

    @Test
    @Transactional
    void AddClientTest() throws Exception {
        try{
            entityManger.createQuery("DELETE FROM OrderItem").executeUpdate();
            entityManger.createQuery("DELETE FROM Order").executeUpdate();
            entityManger.createQuery("DELETE FROM Product ").executeUpdate();
            entityManger.createQuery("DELETE FROM Client ").executeUpdate();
        }catch (Exception ex){

        }
        mockMvc.perform(post("/api/clients")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\r\n    \"name\":\"Ossama\",\r\n    \"lastName\":\"Ismaeel\",\r\n    \"mobile\":\"81737519\"\r\n}"))
                .andExpect(status().isOk());
        mockMvc.perform(post("/api/clients")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\r\n    \"name\":\"Roger\",\r\n    \"lastName\":\"Federer\",\r\n    \"mobile\":\"636969\"\r\n}"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/clients"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$",hasSize(2)))
                .andExpect(jsonPath("$[0].name",is("Ossama")))
                .andExpect(jsonPath("$[1].name",is("Roger")))

                .andExpect(jsonPath("$[0].lastName",is("Ismaeel")))
                .andExpect(jsonPath("$[1].lastName",is("Federer")))
                .andExpect(jsonPath("$[0].mobile",is("81737519")))
                .andExpect(jsonPath("$[1].mobile",is("636969")));
    }

//    @Test
//    @Transactional
//    void EditClientTest() throws Exception {
//        try{
//            entityManger.createQuery("DELETE FROM OrderItem").executeUpdate();
//            entityManger.createQuery("DELETE FROM Order").executeUpdate();
//            entityManger.createQuery("DELETE FROM Product ").executeUpdate();
//            entityManger.createQuery("DELETE FROM Client ").executeUpdate();
//        }catch (Exception ex){
//
//        }
//        mockMvc.perform(post("/api/clients")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content("{\r\n    \"name\":\"Ossama\",\r\n    \"lastName\":\"Ismaeel\",\r\n    \"mobile\":\"81737519\"\r\n}"))
//                .andExpect(status().isOk());
//
//
//        ResultActions resultActions =mockMvc.perform(get("/api/clients"));
//        MvcResult result = resultActions.andReturn();
//        String contentAsString = result.getResponse().getContentAsString();
//
//        SomeCustomResponse response = objectMapper.readValue(contentAsString, SomeCustomResponse.class);
//    }

}