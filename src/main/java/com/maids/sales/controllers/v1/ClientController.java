package com.maids.sales.controllers.v1;

import com.maids.sales.api.v1.contract.client.ClientDTO;
import com.maids.sales.api.v1.mapper.ClientMapper;
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
    private final ClientMapper clientMapper;
    @Autowired
    public ClientController(ClientService clientService, ClientMapper clientMapper) {
        this.clientService = clientService;
        this.clientMapper = clientMapper;
    }

    @GetMapping("/clients")
    @ApiOperation(value = "Get all clients",
    notes = "Returns list with all clients in our system...")
    public List<ClientDTO> findAll() {

        return this.clientService.findAll();
    }

    @GetMapping("/clients/{clientId}")
    public ClientDTO getClient(@PathVariable int clientId) {
        ClientDTO jobType = this.clientService.findById(clientId);
        if (jobType == null) {
            throw new RuntimeException("client id not found- " + clientId);
        }
        return jobType;
    }

    @PostMapping("/clients")
    @ApiOperation(value = "Add new client")
    public ClientDTO add(@RequestBody ClientDTO clientDTO) {
        Client client = clientMapper.clientDTOToClient(clientDTO);
        client.setId(0);

        this.clientService.save(client);

        return clientMapper.clientToClientDTO(client);
    }

    @PutMapping("/clients")
    @ApiOperation(value = "Update client",
            notes = "Update an existing client, note that you have to determine the id of the client in the payload..")
    public ClientDTO updateClient(@RequestBody ClientDTO clientDTO) {
        Client client = clientMapper.clientDTOToClient(clientDTO);
        this.clientService.save(client);
        return clientMapper.clientToClientDTO(client);

    }
}
