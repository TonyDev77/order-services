package com.tony.services.domain.services;

import com.tony.services.domain.exception.DomainException;
import com.tony.services.domain.model.Client;
import com.tony.services.domain.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public Client saveClient(Client client) {

        Client clientExists = clientRepository.findByEmail(client.getEmail());

        if (clientExists != null && !clientExists.equals(client)) {
            throw new DomainException("Já existe um cliente cadastrado com este email");
        }
        return clientRepository.save(client);
    }

    public void removeClient(Long clientId) {
        clientRepository.deleteById(clientId);
    }
}
