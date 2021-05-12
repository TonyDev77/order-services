package com.tony.services.api.controller;

import com.tony.services.domain.model.Client;
import com.tony.services.domain.repository.ClientRepository;
import com.tony.services.domain.services.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clients")
public class ClientController {

    // Informações de log
    private static final Logger log = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ClientService clientService;

    @GetMapping
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    // vincula o parâmetro "clientId" ao PathVariable "clientId"
    @GetMapping("/{clientId}")
    public ResponseEntity<Client> findById(@PathVariable Long clientId) {
        Optional<Client> client = clientRepository.findById(clientId);
        if (client.isPresent()) {
            log.info("Helooooooo world - " + client);
            return ResponseEntity.ok(client.get());
        }
        return ResponseEntity.notFound().build();
    }

    // transforma o corpo da requisição em um objeto Client
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Client saveClient (@Valid @RequestBody Client client) {
        return clientService.saveClient(client);
    }

    // transforma o corpo da requisição em um objeto Client usando ID recebido
    @PutMapping("/{clientId}")
    public ResponseEntity<Client> updateClient(@Valid @PathVariable Long clientId, @RequestBody Client client) {
        if (!clientRepository.existsById(clientId)) {
            return ResponseEntity.notFound().build();
        }
        client.setId(clientId); // seta client com "id" recebido no parâmetro
        client = clientService.saveClient(client);;

        return ResponseEntity.ok(client);
    }

    @DeleteMapping("/{clientId}")
    public ResponseEntity<Void> removeClient(@PathVariable Long clientId) {
        if (!clientRepository.existsById(clientId)) {
            return ResponseEntity.notFound().build();
        }

        clientService.removeClient(clientId);

        return ResponseEntity.noContent().build();
    }
}
