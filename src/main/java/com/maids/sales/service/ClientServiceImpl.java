package com.maids.sales.service;

import com.maids.sales.api.v1.contract.client.ClientDTO;
import com.maids.sales.api.v1.mapper.ClientMapper;
import com.maids.sales.dao.ClientJPARepository;
import com.maids.sales.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class ClientServiceImpl implements  ClientService {
    private ClientJPARepository clientJPARepository;
    private final ClientMapper clientMapper;
    @Autowired
    public ClientServiceImpl(ClientJPARepository clientJPARepository, ClientMapper clientMapper) {
        this.clientJPARepository = clientJPARepository;
        this.clientMapper = clientMapper;
    }

    @Override
    public List<ClientDTO> findAll() {
        return  clientJPARepository.findAll()
                .stream()
                .map(clientMapper::clientToClientDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ClientDTO findById(int id) {
        Optional<Client> result = this.clientJPARepository.findById(id);
        Client client = null;
        if(result.isPresent())
            client = result.get();
        else
            throw new RuntimeException("Did not find client id -" + id);

        return clientMapper.clientToClientDTO(client);
    }

    @Override
    public Client save(Client client) {

            return this.clientJPARepository.save(client);
    }


}
