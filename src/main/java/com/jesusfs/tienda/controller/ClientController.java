package com.jesusfs.tienda.controller;

import com.jesusfs.tienda.domain.client.Client;
import com.jesusfs.tienda.domain.client.dto.CreateClientDTO;
import com.jesusfs.tienda.domain.client.dto.ResponseClientDTO;
import com.jesusfs.tienda.domain.client.dto.UpdateClientDTO;
import com.jesusfs.tienda.domain.client.ClientService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
@AllArgsConstructor
@PreAuthorize("denyAll()")
public class ClientController {

    private ClientService clientService;

    @GetMapping
    @PreAuthorize("hasAnyRole({'ADMIN', 'DEVELOPER', 'WORKER'})")
    public List<ResponseClientDTO> getUsers() {
        List<Client> clients = clientService.getClients();
        return clients.stream().map(ResponseClientDTO::new).toList();
    }

    @PostMapping
    @PreAuthorize("hasAnyRole({'ADMIN', 'DEVELOPER', 'WORKER'})")
    public ResponseClientDTO createClient(@Valid @RequestBody CreateClientDTO clientDTO) {
        Client client = clientService.createClient(clientDTO);
        return new ResponseClientDTO(client);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole({'ADMIN', 'DEVELOPER', 'WORKER'})")
    public ResponseClientDTO updateClient(@PathVariable Long id, @Valid @RequestBody UpdateClientDTO clientDTO) {
        Client client = clientService.updateClient(id, clientDTO);
        return new ResponseClientDTO(client);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole({'ADMIN', 'DEVELOPER'})")
    public ResponseEntity<?> deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole({'ADMIN', 'DEVELOPER', 'WORKER'})")
    public ResponseClientDTO getClientById(@PathVariable Long id) {
        Client client = clientService.getClientById(id);
        return new ResponseClientDTO(client);
    }
}
