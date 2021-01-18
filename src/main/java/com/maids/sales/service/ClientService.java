package com.maids.sales.service;

import com.maids.sales.api.v1.contract.client.ClientDTO;
import com.maids.sales.entity.Client;


import java.util.List;

public interface ClientService {
    public List<ClientDTO> findAll();
    public ClientDTO findById(int id);

    public Client save(Client client);


}
