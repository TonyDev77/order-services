package com.tony.services.domain.repository;

import com.tony.services.domain.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    // MÉTODOS PERSONALIZADOS
    List<Client> findByName(String name); // busca por nome
    List<Client> findByNameContaining(String name); // busca por fragmento de nome
    Client findByEmail(String email);
}
