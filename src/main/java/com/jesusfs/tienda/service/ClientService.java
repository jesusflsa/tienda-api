package com.jesusfs.tienda.service;

import com.jesusfs.tienda.model.Client;
import com.jesusfs.tienda.dto.client.CreateClientDTO;
import com.jesusfs.tienda.dto.client.UpdateClientDTO;
import com.jesusfs.tienda.repository.ClientRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public Client createClient(@Valid CreateClientDTO clientDTO) {
        // Validations
        // Unique DNI
        Optional<Client> opClient = clientRepository.findByDni(clientDTO.dni());
        if (opClient.isPresent())
            throw new RuntimeException("DNI is taken. Use another.");
        // Unique phone
        if (clientDTO.phone() != null) {
            opClient = clientRepository.findByPhone(clientDTO.phone());
            if (opClient.isPresent())
                throw new RuntimeException("Phone is taken. Use another.");
        }
        // Creating client
        Client client = new Client(clientDTO);
        return clientRepository.save(client);
    }

    public boolean deleteClient(Long id) {
        // Validations
        // Client not exists or is not active
        Optional<Client> opClient = clientRepository.findByIdAndActiveTrue(id);
        if (opClient.isEmpty())
            return false;

        // Deleting client
        Client client = opClient.get();
        client.setActive(false);
        clientRepository.save(client);
        return true;
    }

    public Client updateClient(Long id, @Valid UpdateClientDTO clientDTO) {
        // Validations
        Optional<Client> opClient;
        // DNI is taken
        opClient = clientRepository.findByDniAndIdNot(clientDTO.dni(), id);
        if (opClient.isPresent())
            throw new RuntimeException("DNI is taken. Please use another.");

        // Phone is taken
        if (clientDTO.phone() != null) {
            opClient = clientRepository.findByPhoneAndIdNot(clientDTO.phone(), id);
            if (opClient.isPresent())
                throw new RuntimeException("Phone is taken. Please use another.");
        }

        // Client not exists
        opClient = clientRepository.findById(id);
        if (opClient.isEmpty())
            throw new RuntimeException("Client not exists");

        // Updating client
        Client client = opClient.get();
        client.update(clientDTO);
        return clientRepository.save(client);
    }

    public Client getClientById(Long id) {
        // Validations
        // Client not exists
        Optional<Client> opClient = clientRepository.findById(id);
        if (opClient.isEmpty())
            throw new RuntimeException("Client not exists.");

        // Getting client
        return opClient.get();
    }

    public List<Client> getClients() {
        return clientRepository.findByActiveTrue();
    }
}
