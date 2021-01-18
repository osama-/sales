package com.maids.sales.controllers.v1;

import com.maids.sales.api.v1.contract.client.ClientDTO;
import com.maids.sales.entity.Client;
import com.maids.sales.service.ClientService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ClientController {
    private ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/clients")
    @ApiOperation(value = "Get all clients",
    notes = "Returns list with all clients in our system...")
    public List<ClientDTO> findAll() {

        return this.clientService.findAll();
    }

    @GetMapping("/clients/{clientId}")
    public ClientDTO getProduct(@PathVariable int clientId) {
        ClientDTO jobType = this.clientService.findById(clientId);
        if (jobType == null) {
            throw new RuntimeException("client id not found- " + clientId);
        }
        return jobType;
    }

    @PostMapping("/clients")
    @ApiOperation(value = "Add new client")
    public Client add(@RequestBody Client client) {

        client.setId(0);

        this.clientService.save(client);
        return client;
    }

    @PutMapping("/clients")
    @ApiOperation(value = "Update client",
            notes = "Update an existing client, note that you have to determine the id of the client in the payload..")
    public Client updateClient(@RequestBody Client client) {

        this.clientService.save(client);
        return client;

    }
}
