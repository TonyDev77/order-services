package com.tony.services.api.controller;

import com.tony.services.domain.model.Client;
import com.tony.services.domain.repository.ClientRepository;
import com.tony.services.domain.services.ClientRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ClientRegisterService clientRegisterService;

    @GetMapping("/clients")
    public List<Client> findAll() {
        return clientRepository.findAll();
        //return clientRepository.findByName("Joao");
        //return clientRepository.findByNameContaining("o");
    }

    // vincula o parâmetro "clientId" ao PathVariable "clientId"
    @GetMapping("clients/{clientId}")
    public ResponseEntity<Client> findById(@PathVariable Long clientId) {
        Optional<Client> client = clientRepository.findById(clientId);
        if (client.isPresent()) {
            return ResponseEntity.ok(client.get());
        }
        return ResponseEntity.notFound().build();
    }

    // transforma o corpo da requisição em um objeto Client
    @PostMapping("/clients")
    @ResponseStatus(HttpStatus.CREATED)
    public Client saveClient (@Valid @RequestBody Client client) {
        return clientRegisterService.saveClient(client);
    }

    // transforma o corpo da requisição em um objeto Client usando ID recebido
    @PutMapping("/clients/{clientId}")
    public ResponseEntity<Client> updateClient(@Valid @PathVariable Long clientId, @RequestBody Client client) {
        if (!clientRepository.existsById(clientId)) {
            return ResponseEntity.notFound().build();
        }
        client.setId(clientId); // seta client com "id" recebido no parâmetro
        client = clientRegisterService.saveClient(client);;

        return ResponseEntity.ok(client);
    }

    @DeleteMapping("/clients/{clientId}")
    public ResponseEntity<Void> removeClient(@PathVariable Long clientId) {
        if (!clientRepository.existsById(clientId)) {
            return ResponseEntity.notFound().build();
        }

        clientRegisterService.removeClient(clientId);

        return ResponseEntity.noContent().build();
    }
}
