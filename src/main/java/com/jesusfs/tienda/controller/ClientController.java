package com.jesusfs.tienda.controller;

import com.jesusfs.tienda.model.client.Client;
import com.jesusfs.tienda.model.client.CreateClientDTO;
import com.jesusfs.tienda.model.client.ResponseClientDTO;
import com.jesusfs.tienda.model.client.UpdateClientDTO;
import com.jesusfs.tienda.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @GetMapping
    public List<ResponseClientDTO> getUsers() {
        List<Client> clients = clientService.getClients();
        return clients.stream().map(ResponseClientDTO::new).toList();
    }

    @PostMapping
    public ResponseClientDTO createClient(@Valid @RequestBody CreateClientDTO clientDTO) {
        Client client = clientService.createClient(clientDTO);
        return new ResponseClientDTO(client);
    }

    @PutMapping("/{id}")
    public ResponseClientDTO updateClient(@PathVariable Long id, @Valid @RequestBody UpdateClientDTO clientDTO) {
        Client client = clientService.updateClient(id, clientDTO);
        return new ResponseClientDTO(client);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteClient(@PathVariable Long id) {
        boolean res = clientService.deleteClient(id);
        return res ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseClientDTO getClientById(@PathVariable Long id) {
        Client client = clientService.getClientById(id);
        return new ResponseClientDTO(client);
    }
}
